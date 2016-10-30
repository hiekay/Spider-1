package com.spider.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spider.bean.AjaxInput;
import com.spider.bean.Clothes;
import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.bean.RuleException;
import com.spider.util.TextUtil;

public class ExtractServiceMoGu {

	/**
	 * 依照相应的规则解析数据
	 * 
	 * @param rule
	 *            连接的规则类
	 * @param response
	 *            登录返回的http Response,需要登录验证时返回的cookie
	 * @return
	 */
	public static Elements extract(Rule rule, Response response) {

		// 进行对rule的必要校验
		validateRule(rule);

		Elements results = null;
		try {
			/**
			 * 解析rule
			 */
			String url = rule.getUrl();
			Map<String, String> map = rule.getMap();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			// 模拟浏览器登录
			Connection conn = Jsoup
					.connect(url)
					.userAgent(
							"Mozilla/5.0 "
									+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
									+ " Chrome/26.0.1410.64 Safari/537.31")
					.ignoreHttpErrors(true);

			// 设置cookies
			if (response != null) {
				conn.cookies(response.cookies());
			}

			// 设置查询参数
			if (map.size() != 0) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					conn.data(entry.getKey(), entry.getValue());
				}

			}

			// 设置请求类型
			Document doc = null;
			switch (requestType) {
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			}

			// 处理返回数据
			results = new Elements();
			switch (type) {
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
				// 当resultTagName为空时默认去body标签
				if (TextUtil.isEmpty(resultTagName)) {
					results = doc.getElementsByTag("body");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return results;
	}

	/**
	 * 从网页中寻找超链接
	 * 
	 * @param results
	 * @return
	 */
	public static List<LinkTypeData> searchHref(Elements results) {

		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		for (Element result : results) {
			Elements links = result.getElementsByTag("a");

			for (Element link : links) {
				// 必要的筛选
				String linkHref = link.attr("href");
				// String linkHref = "";
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
	 * 
	 * @param results
	 * @return
	 */
	public static List<LinkTypeData> searchTable(Elements results) {

		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		for (Element result : results) {
			Elements tables = result.getElementsByTag("table"); // 筛析表格内容
			Element block1 = tables.get(0); // 选取第一个表格
			Elements block2 = block1.select("td[class=tdleft]"); // 依照css取得内容
			for (Element e : block2) {
				System.out.println(e.text().toString().trim());
			}

		}

		return datas;
	}

	/**
	 * 从网页中寻找隐藏输入框
	 * 
	 * @param results
	 * @return
	 */
	public static List<AjaxInput> searchInput(Elements results) {

		List<AjaxInput> datas = new ArrayList<AjaxInput>();
		for (Element result : results) {
			Elements links = result.getElementsByTag("input");

			for (Element link : links) {
				// 必要的筛选
				String paramName = link.attr("param-name");
				// String paramName = "";
				String value = link.attr("value");

				AjaxInput data = new AjaxInput();
				data.setParamName(paramName);
				data.setValue(value);

				datas.add(data);
			}
		}

		return datas;
	}

	/**
	 * 蘑菇街中搜索结果对应的多个商品
	 * 
	 * @param results
	 *            搜索结果的元素集
	 * @return 商品信息列表
	 */
	public static List<Clothes> searchListInfo(Elements results,
			Response response) {
		List<AjaxInput> datas = searchInput(results);
		List<Clothes> clothesList = new ArrayList<Clothes>();
		int num = 0; // 限制数量
		String url = "http://list.mogujie.com/search?";

		for (int i = 0; i < datas.size() && num < 5; i++) {// 构造Ajax请求
			url += "&";
			url += datas.get(i).getParamName();
			url += "=";
			url += datas.get(i).getValue();
			System.out.println(i);
		}
		System.out.println(url);
		/* 获取json */
		clothesList = getJson(url);

		return clothesList;
	}

	/* 获取json数据 */
	public static List<Clothes> getJson(String url) {
		String result = "";
		BufferedReader in = null;
		int num = 20;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		// 解析,
		JSONObject jsonObj = JSON.parseObject(result).getJSONObject("result")
				.getJSONObject("wall");
		JSONArray jarr = jsonObj.getJSONArray("docs");
		List<Clothes> clothesList = new ArrayList<Clothes>();
		for (int i = 0; i < num; i++) {
			JSONObject Obj = (JSONObject) jarr.get(i);
			Clothes clothes = new Clothes();
			clothes.setName(Obj.get("title").toString());
			clothes.setOriginPrice(Obj.get("orgPrice").toString()
					.replace("￥", ""));
			clothes.setBarginPrice(Obj.get("price").toString());
			clothes.setUrl(Obj.get("link").toString());
			clothes.setAgreeNum(Obj.get("cfav").toString());
			clothes.setPic(Obj.get("img").toString());
			clothesList.add(clothes);
		}

		return clothesList;

	}

	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule) {
		String url = rule.getUrl();
		if (TextUtil.isEmpty(url)) {
			throw new RuleException("url不能为空！");
		}
		if (!(url.startsWith("http://") || url.startsWith("https://"))) {
			throw new RuleException("url的格式不正确！");
		}

	}

}
