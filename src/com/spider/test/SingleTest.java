package com.spider.test;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.select.Elements;

import com.spider.bean.Rule;
import com.spider.service.ExtractServiceMoGu;

public class SingleTest {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		String url = "";
        Rule rule = new Rule(url,
                map,  null, -1, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractServiceMoGu.extract(rule, null);
        /*获取对应的内容*/
        /*Clothes clothes = ExtractService.searchInfo(results);*/
        /*System.out.println(clothes.toString());*/

	}

}
