package com.spider.bean;

import java.util.Map;

/**
 * 规则类
 * 
 */
public class Rule {

	private String url;// 链接
	private Map<String, String> map;// 参数集合及其对应的值
	/**
	 * 对返回的HTML，第一次过滤所用的标签，请先设置type
	 */
	private String resultTagName;

	/**
	 * CLASS / ID / SELECTION 设置resultTagName的类型，默认为ID
	 */
	private int type = ID; // 筛选条件

	/**
	 * GET / POST 请求的类型，默认GET
	 */
	private int requestMoethod = GET;

	public final static int GET = 0;
	public final static int POST = 1;

	public final static int CLASS = 0;
	public final static int ID = 1;
	public final static int SELECTION = 2;

	public Rule() {
	}

	public Rule(String url, Map<String,String> map, String resultTagName, int type,
			int requestMoethod) {
		super();
		this.url = url;
		this.map = map;
		this.resultTagName = resultTagName;
		this.type = type;
		this.requestMoethod = requestMoethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResultTagName() {
		return resultTagName;
	}

	public void setResultTagName(String resultTagName) {
		this.resultTagName = resultTagName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRequestMoethod() {
		return requestMoethod;
	}

	public void setRequestMoethod(int requestMoethod) {
		this.requestMoethod = requestMoethod;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
