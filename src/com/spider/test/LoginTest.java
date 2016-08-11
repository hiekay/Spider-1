package com.spider.test;

import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.spider.bean.LinkTypeData;
import com.spider.core.ExtractService;
import com.spider.core.LoginService;
import com.spider.core.PrintService;
import com.spider.core.SearchService;
import com.spider.rule.Rule;

public class LoginTest {
	
	  public static void main(String[] args)throws Exception {
		    
		    LoginService ls = new LoginService();
		    SearchService ss = new SearchService();
		    
		    Response response = ls.login("***", "***");//输入用户名，和密码
		    ss.searchInfo(response);
		    
//	        Rule rule = new Rule("http://write.blog.csdn.net/postlist",  
//	                new String[] {}, new String[] {},  
//	                "a", Rule.SELECTION, Rule.POST);  
//	        List<LinkTypeData> extracts = ExtractService.extract(rule);  
//	        PrintService.printf(extracts);
	        
	 }

}
