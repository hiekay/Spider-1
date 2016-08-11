package com.spider.test;

import java.util.List;

import org.jsoup.Connection.Method;
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
		    
		    Response response = ls.login("****", "****");//输入用户名，和密码
            
//            Response response2 = Jsoup.connect("http://write.blog.csdn.net/category").userAgent("Mozilla/5.0 "
//					+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
//					+ " Chrome/26.0.1410.64 Safari/537.31")
//                    .cookies(response.cookies()).method(Method.GET).execute();
		    
//		    ss.searchInfo(response2);
		    
	        Rule rule = new Rule("http://write.blog.csdn.net/category",  
	                new String[] {}, new String[] {},  
	                "a", Rule.SELECTION, Rule.POST);  
	        List<LinkTypeData> extracts = ExtractService.extract(rule, response);  
	        PrintService.printf(extracts);
	        
	 }

}
