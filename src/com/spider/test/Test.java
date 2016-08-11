package com.spider.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.SAXException;


import org.jsoup.Connection.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
 
    private static Document doc;
    private static String url = "http://xjk.med.stu.edu.cn";
    private static Map<String, String> data = new HashMap<>();
 
    public static void main(String [] args){
        try {
        	data.put("UserID","2014710017");
        	data.put("password","620325");
        	
            Response res = Jsoup.connect(url)
                    .data(data).method(Method.POST).execute();
            
            String sessionId = res.cookie("PHPSESSID"); 
            System.out.println(sessionId);
            
            Response res2 = Jsoup.connect("http://news.baidu.com/")
                    .cookie("PHPSESSID",sessionId).method(Method.GET).execute();
            
            doc = res2.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
 
        Elements tables = doc.getElementsByTag("table");
        Element block1 = tables.get(9);
        Elements block2 = block1.select("tr[bgcolor=#ECF3F6]");
        for (Element e:block2) {
            System.out.println(e.text().toString().trim());
        }
    }
}
