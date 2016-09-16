package com.spider.bean;

public class AjaxInput {
	private String paramName;
	private String value;
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName.replace(" ", "");;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value.replace(" ", "");;
	}
	
	
}
