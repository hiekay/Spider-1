package com.spider.test;

import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.select.Elements;

import com.spider.bean.Book;
import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.service.ExtractService;
import com.spider.service.PrintService;
import com.spider.service.SearchService;
/**
 * 当当网内容搜索
 * @author LIn
 *
 */
public class ContentTest {
	
	public static void main(String[] args) {
	    /*当当网商品爬取*/
//        Rule rule = new Rule("http://search.dangdang.com",  
//                new String[] {"key"}, new String[] {"白说"},  
//                "pic", Rule.CLASS, Rule.GET);
        Rule rule = new Rule("http://product.dangdang.com/1122736971.html",  
                new String[] {}, new String[] {},  
                null, -1, Rule.GET);
        
        Response response = null;
        /*处理返回数据*/
        Elements results = ExtractService.extract(rule, response);
        /*获取对应的内容*/
        Book b = ExtractService.searchInfo(results);

        
//        SearchService ss = new SearchService();
        
        
	}

}
