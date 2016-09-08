package com.spider.test;

import java.util.List;

import org.jsoup.select.Elements;

import com.spider.bean.Clothes;
import com.spider.bean.LinkTypeData;
import com.spider.bean.Rule;
import com.spider.service.ExtractServiceMoGu;
import com.spider.service.PrintService;

public class SingleTest {

	public static void main(String[] args) {
        Rule rule = new Rule("http://shop.mogujie.com/detail/1k1103o?acm=2.ms.1_1.1.1357-5081-5024-5535.yMwpVr6HfQQ.t_176-c_1_3_78742768_1175669_1-c1_&cparam=MTQ3MjYyNjAxOF8xMmxycDhrXzUwMTI4ZmNkZjM0NGU2NjNmNDM3ZjkxMmMxMTljZTkxXzNfMF83ODc0Mjc2OF8wLjU4MzlfMTE3NTY2OV8x&ptp=1.9evzO._book_shopping_51082_pc-wall-v1_noab-noab_wall_cpc.1.GR2Ms",  
                new String[] {}, new String[] {},  
                null, -1, Rule.GET);
        
        /*处理返回数据*/
        Elements results = ExtractServiceMoGu.extract(rule, null);
        /*获取对应的内容*/
        /*Clothes clothes = ExtractService.searchInfo(results);*/
        /*System.out.println(clothes.toString());*/

	}

}
