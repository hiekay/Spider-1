package com.spider.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.select.Elements;

import com.spider.bean.Book;
import com.spider.bean.Rule;
import com.spider.service.ExtractService;

public class SearchInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		System.out.println(name);
		String url = "http://search.dangdang.com";
		
		/*在建立一次http握手后,保存cookie,以免每次都需建立连接*/
		Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 "
				+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
				+ " Chrome/26.0.1410.64 Safari/537.31");
		Response r = con.ignoreContentType(true).method(Method.GET).execute();
		
	    /*当当网商品爬取*/
        Rule rule = new Rule(url,  
                new String[] {"key"}, new String[] {name},  
                "pic", Rule.CLASS, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractService.extract(rule, r);
        /*获取对应的内容*/

        List<Book> booklist = ExtractService.searchListInfo(results, r);

        for (int i = 0; i < booklist.size(); i++) {
			System.out.println(booklist.get(i).toString());
		}
        
		//request生命周期较短，将数据返回到显示页面
		request.setAttribute("bl", booklist);
		
		request.getRequestDispatcher("show.jsp").forward(request, response);
	}

}
