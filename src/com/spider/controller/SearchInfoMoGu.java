package com.spider.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.select.Elements;

import com.spider.bean.Clothes;
import com.spider.bean.Rule;
import com.spider.service.ExtractServiceMoGu;

public class SearchInfoMoGu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchInfoMoGu() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		System.out.println(name);
		String url = "http://list.mogujie.com/s?";
		
		/*在建立一次http握手后,保存cookie,以免每次都需建立连接*/
		Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 "
				+ "(Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko)"
				+ " Chrome/26.0.1410.64 Safari/537.31");
		Response r = con.ignoreContentType(true).method(Method.GET).execute();
		
	    /*蘑菇街商品爬取*/
        Rule rule = new Rule(url,  
                new String[] {"q"}, new String[] {name},  
                "ajax_param", Rule.CLASS, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractServiceMoGu.extract(rule, r);
        System.out.println(results);
        /*获取对应的内容*/

        List<Clothes> clotheslist = ExtractServiceMoGu.searchListInfo(results, r);

        for (int i = 0; i < clotheslist.size(); i++) {
			System.out.println(clotheslist.get(i).toString());
		}
        
		//request生命周期较短，将数据返回到显示页面
		request.setAttribute("cl", clotheslist);
		
		request.getRequestDispatcher("showMoGu.jsp").forward(request, response);
	}

}
