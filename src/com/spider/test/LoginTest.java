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
import com.spider.service.ExtractServiceMoGu;
import com.spider.service.LoginService;
import com.spider.service.PrintService;

/**
 * csdn模拟登录,获取表格内容
 * @author LIn
 *
 */
public class LoginTest {
	
	  public static void main(String[] args)throws Exception {
		    
		    LoginService ls = new LoginService();
		    
		    /*输入用户名和密码以模拟登录  */
		    Response response = ls.login("***", "***");

		    /*设置爬取规则*/
	        Rule rule = new Rule("http://write.blog.csdn.net/category",  
	                new String[] {}, new String[] {},  
	                "body", Rule.SELECTION, Rule.POST);
	        
	        /*处理返回数据*/
	        Elements results = ExtractServiceMoGu.extract(rule, response);
	        /*获取对应的内容*/
	        List<LinkTypeData> extracts = ExtractServiceMoGu.searchTable(results);
	        PrintService.printf(extracts);
	         
	 }

}
