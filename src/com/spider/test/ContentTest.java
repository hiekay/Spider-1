package com.spider.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.select.Elements;

import com.spider.bean.Clothes;
import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.service.ExtractServiceMoGu;
import com.spider.service.PrintService;
/**
 * 蘑菇街内容搜索
 * @author LIn
 *
 */
public class ContentTest {
	
	public static void main(String[] args) throws IOException {
		String url = "http://www.mogujie.com/";
		Map<String, String> map = new HashMap<>();
		
		/*在建立一次http握手后,保存cookie,以免每次都需建立连接*/
		Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 "
				+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
				+ " Chrome/26.0.1410.64 Safari/537.31");
		Response response = con.ignoreContentType(true).method(Method.GET).execute();
		
	    /*蘑菇街商品爬取*/
		map.put("key", "摩托车");
        Rule rule = new Rule(url, map,  
                "pic", Rule.CLASS, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractServiceMoGu.extract(rule, response);
        /*获取对应的内容*/

        List<Clothes> booklist = ExtractServiceMoGu.searchListInfo(results, response);

        for (int i = 0; i < booklist.size(); i++) {
			System.out.println(booklist.get(i).toString());
		}
        
	}

}
