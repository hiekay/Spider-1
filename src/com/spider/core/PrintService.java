package com.spider.core;

import java.util.List;

import com.spider.bean.LinkTypeData;

public class PrintService {
	
	public static void printf(List<LinkTypeData> datas)
	{
		for (LinkTypeData data : datas)
		{
			System.out.println(data.getLinkText());
			System.out.println(data.getLinkHref());
			System.out.println("***********************************");
		}

	}

}
