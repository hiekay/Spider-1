package com.spider.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.spider.bean.Book;
import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.bean.RuleException;
import com.spider.util.TextUtil;

public class ExtractService
{

	/**
	 * 依照相应的规则解析数据
	 * @param rule      连接的规则类
	 * @param response  登录返回的http Response,需要登录验证时返回的cookie
	 * @return          
	 */
	public static Elements extract(Rule rule, Response response)
	{

		// 进行对rule的必要校验
		validateRule(rule);

		Elements results = null;
		try
		{
			/**
			 * 解析rule
			 */
			String url = rule.getUrl();
			String[] params = rule.getParams();
			String[] values = rule.getValues();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			//模拟浏览器登录
			Connection conn = Jsoup.connect(url).userAgent("Mozilla/5.0 "
					+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
					+ " Chrome/26.0.1410.64 Safari/537.31").ignoreHttpErrors(true);
			
			//设置cookies
			if(response != null){
				conn.cookies(response.cookies());
			}
			
			// 设置查询参数
			if (params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					conn.data(params[i], values[i]);
				}
			}

			// 设置请求类型
			Document doc = null;
			switch (requestType)
			{
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			}

			//处理返回数据
			results = new Elements();
			switch (type)
			{
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				//当resultTagName为空时默认去body标签
				if (TextUtil.isEmpty(resultTagName))
				{
					results = doc.getElementsByTag("body");
				}
			}


		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return results;
	}
	
	/**
	 * 从网页中寻找超链接
	 * @param results
	 * @return
	 */
	public static List<LinkTypeData> searchHref(Elements results){
		
		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		for (Element result : results)
		{
			Elements links = result.getElementsByTag("a");

			for (Element link : links)
			{
				//必要的筛选
				String linkHref = link.attr("href"); 
//				String linkHref = "";
				String linkText = link.text();
				
				
				LinkTypeData data = new LinkTypeData();
				data.setLinkHref(linkHref);
				data.setLinkText(linkText);

				datas.add(data);
			}
		}
		
		return datas;
	}
	
	/**
	 * 从网页中寻找表格
	 * @param results
	 * @return
	 */
	public static List<LinkTypeData> searchTable(Elements results){
		
		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		for (Element result : results)
		{
			Elements tables = result.getElementsByTag("table");  //筛析表格内容
			Element block1 = tables.get(0);  //选取第一个表格
	        Elements block2 = block1.select("td[class=tdleft]"); //依照css取得内容
	        for (Element e:block2) {
	            System.out.println(e.text().toString().trim());
	        }

		}
		
		return datas;
	}
	
	/**
	 * 当当网中搜索结果对应的多个书籍
	 * @param results  搜索结果的元素集
	 * @return  书籍信息列表
	 */
	public static List<Book> searchListInfo(Elements results,Response response){
		List<LinkTypeData> datas = searchHref(results);
		List<Book> booklist = new ArrayList<Book>();
		int num = 0;  //限制数量
		
		for (int i = 0; i < datas.size() && num < 3; i++) {
			String url = datas.get(i).getLinkHref();  //获取超链接
			System.out.println(i);
	        Rule rule = new Rule(url,  
	                new String[] {}, new String[] {},  
	                null, -1, Rule.GET);     //设置搜索规则
	        
	        /*处理返回数据*/
	        Elements e = ExtractService.extract(rule, response);
	        /*获取对应的内容*/
	        Book b = ExtractService.searchInfo(e);
	        
	        if(b != null){
	        	num++;
	        	b.setUrl(url);
	        	booklist.add(b);
	        }
	        
		}
		
		return booklist;
	}
	
	/**
	 * 当当网单一商品数据
	 * @param results
	 * @return 
	 */
	public static Book searchInfo(Elements results){
		
		Element result = results.get(0);
		String name;
		String price;
		String author;
		String publishor;
		String time;
		String url;
		String image;
		
		/*从网页元素里读取数据
		 * 按照类型或id读取相应的数据*/
		/*需根据网页的不同样式来选择不同方式获取数据*/
		if(result.getElementById("salePriceTag") != null){  //当当商家
			name = result.getElementsByClass("head").text().toString();
			price = result.getElementById("salePriceTag").text().toString();
			
			Elements info = result.getElementsByClass("show_info_right");
			
			/*非书籍类商品没有以下属性*/
			if(info.size() > 7){
				author = info.get(6).text().toString();
				publishor = info.get(7).text().toString();
				time = info.get(8).text().toString();
			}else{
				author = null;
				publishor = null;
				time = null;
			}
			image = result.getElementById("largePic").attr("src");
			
		}else if(result.getElementById("price_sale") != null){  //当当自营
			name = result.getElementsByClass("name_info").text().toString();
			price = result.getElementById("price_sale").text().toString();
			
			Elements info = result.getElementsByClass("t1");
			author = info.get(0).text().toString();
			publishor = info.get(1).text().toString();
			time = info.get(2).text().toString();
			image = result.getElementById("largePic").attr("src");
		}else{
			return null;
		}
		
		/*对获取后的数据进行处理,使格式统一*/
		price = price.replaceFirst("¥", "");
		author = author.replaceFirst("作者:", "");
		publishor = publishor.replaceFirst("出版社:", "");
		time = time.replaceFirst("出版时间:", "");
		
		/*将数据封装在模型中*/
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setAuthor(author);
		book.setPublishor(publishor);
		book.setTime(time);
		book.setImage(image);

		return book;
	}
	
	
	
	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule)
	{
		String url = rule.getUrl();
		if (TextUtil.isEmpty(url))
		{
			throw new RuleException("url不能为空！");
		}
		if (!(url.startsWith("http://") || url.startsWith("https://")))
		{
			throw new RuleException("url的格式不正确！");
		}

		if (rule.getParams() != null && rule.getValues() != null)
		{
			if (rule.getParams().length != rule.getValues().length)
			{
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}


}
