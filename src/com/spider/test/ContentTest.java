package com.spider.test;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.select.Elements;

import com.spider.bean.Book;
import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.service.ExtractService;
import com.spider.service.PrintService;
/**
 * 当当网内容搜索
 * @author LIn
 *
 */
public class ContentTest {
	
	public static void main(String[] args) throws IOException {
		String url = "http://search.dangdang.com";
		
		/*在建立一次http握手后,保存cookie,以免每次都需建立连接*/
		Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 "
				+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
				+ " Chrome/26.0.1410.64 Safari/537.31");
		Response response = con.ignoreContentType(true).method(Method.GET).execute();
		
	    /*当当网商品爬取*/
        Rule rule = new Rule(url,  
                new String[] {"key"}, new String[] {"白说"},  
                "pic", Rule.CLASS, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractService.extract(rule, response);
        /*获取对应的内容*/

        List<Book> booklist = ExtractService.searchListInfo(results, response);

        for (int i = 0; i < booklist.size(); i++) {
			System.out.println(booklist.get(i).toString());
		}
        
	}

}
