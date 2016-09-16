package com.spider.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class JsonTest {
	
	public static String loadJson (String url) {  
        StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
                json.append(inputLine);  
            }  
            in.close();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return json.toString();  
    }
	
	public static void main(String[] args) {  
        String url = "http://list.mogujie.com/search?&priceList=[50, 80, 80, 90, 90, 100]&_version=1&_mgjuuid=049853e0-c131-cb98-2638-cf83a645452b&sort=pop&cpc_offset=&cKey=pc-search-wall&page=1&q=%E5%B0%8F%E6%B8%85%E6%96%B0%E8%BF%9E%E8%A1%A3%E8%A3%99&userId=&ppath=&maxPrice=&minPrice=";  
//        String url = "http://www.kuaidi100.com/query?type=yunda&postid=1201386764793";  
        String json = loadJson(url);  
        System.out.println(json);  
    }  
}
