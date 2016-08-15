package com.spider.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;

public class SearchService {

	public void searchInfo(String url){
	    /*设置爬取规则*/
        Rule rule = new Rule(url,  
                new String[] {}, new String[] {},  
                "body", Rule.SELECTION, Rule.POST);
        
        /*处理返回数据*/
        Elements results = ExtractService.extract(rule, null);
        /*获取对应的内容*/
        List<LinkTypeData> extracts = ExtractService.searchTable(results);
		
	}
	
	public void searchListInfo(List<LinkTypeData> list){
		for (int i = 0; i < list.size(); i++) {
			String url = list.get(i).getLinkHref();
			searchInfo(url);
		}
	}
}
