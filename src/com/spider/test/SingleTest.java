package com.spider.test;

import java.util.List;

import org.jsoup.select.Elements;

import com.spider.bean.Book;
import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.service.ExtractService;
import com.spider.service.PrintService;

public class SingleTest {

	public static void main(String[] args) {
        Rule rule = new Rule("http://product.dangdang.com/1070595448.html",  
                new String[] {}, new String[] {},  
                null, -1, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractService.extract(rule, null);
        /*获取对应的内容*/
        Book b = ExtractService.searchInfo(results);
        System.out.println(b.toString());

	}

}
