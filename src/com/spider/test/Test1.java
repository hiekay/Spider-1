package com.spider.test;

import java.util.List;

import com.spider.bean.LinkTypeData;
import com.spider.core.ExtractService;
import com.spider.core.PrintService;
import com.spider.rule.Rule;

public class Test1 {
	public static void main(String[] args) {
//		LoginTest.testFormSubmit();

//        Rule rule = new Rule("http://news.baidu.com/",  
//                new String[] {}, new String[] {},  
//                "a", Rule.SELECTION, Rule.GET);  
//        List<LinkTypeData> extracts = ExtractService.extract(rule);  
//        printf(extracts);
		
		Rule rule = new Rule("http://blog.csdn.net/why_still_confused",  
                new String[] {}, new String[] {},  
                "a", Rule.SELECTION, Rule.GET);  
        List<LinkTypeData> extracts = ExtractService.extract(rule);  
        PrintService.printf(extracts);
	
	}

}
