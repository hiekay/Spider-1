package com.spider.test;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.service.ExtractService;
import com.spider.service.LoginService;
import com.spider.service.PrintService;
import com.spider.service.SearchService;

public class LoginTest {
	
	  public static void main(String[] args)throws Exception {
		    
//		    LoginService ls = new LoginService();
//		    SearchService ss = new SearchService();
//		    
//		    Response response = ls.login("***", "***");//输入用户名，和密码
            
//            Response response2 = Jsoup.connect("http://write.blog.csdn.net/category").userAgent("Mozilla/5.0 "
//					+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
//					+ " Chrome/26.0.1410.64 Safari/537.31")
//                    .cookies(response.cookies()).method(Method.GET).execute();
		    
//		    ss.searchInfo(response2);
		    
	        Rule rule = new Rule("https://www.baidu.com/s",  
	                new String[] {"wd"}, new String[] {"apple"},  
	                null, -1, Rule.GET);
	        
//	        Rule rule = new Rule("https://s.taobao.com/search",  
//	                new String[] {"q"}, new String[] {"apple"},  
//	                null, -1, Rule.GET);
	        
	        Response response = null;
	        /*处理返回数据*/
	        Elements results = ExtractService.extract(rule, response);
	        /*获取对应的内容*/
	        List<LinkTypeData> extracts = ExtractService.searchHref(results);
	        PrintService.printf(extracts);
	         
	 }

}
