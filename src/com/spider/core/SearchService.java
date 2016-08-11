package com.spider.core;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

public class SearchService {

	public void searchInfo(Response response){
		Document doc = null;
		
		try {
			doc = response.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(doc);
	}
}
