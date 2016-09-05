package com.spider.test;

import org.jsoup.select.Elements;

import com.spider.bean.Book;
import com.spider.bean.Rule;
import com.spider.service.ExtractService;

public class SingleTest {

	public static void main(String[] args) {
        Rule rule = new Rule("http://search.dangdang.com",  
                new String[] {"key"}, new String[] {"摩托车"},  
                "pic", Rule.CLASS, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractService.extract(rule, null);
        /*获取对应的内容*/
        Book b = ExtractService.searchInfo(results);
        System.out.println(b.toString());

	}

}
