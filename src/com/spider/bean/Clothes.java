package com.spider.bean;

public class Clothes {
	private String name;     //商品名
	private String pic;     //图片
	private String originPrice;    //原价
	private String barginPrice;   //促销价
	private String agreeNum;     //喜爱度	
	private String url;      //购买链接

	public Clothes(){
		
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOriginPrice() {
		return originPrice;
	}


	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public void setOriginPrice(String originPrice) {
		this.originPrice = originPrice;
	}


	public String getBarginPrice() {
		return barginPrice;
	}


	public void setBarginPrice(String barginPrice) {
		this.barginPrice = barginPrice;
	}


	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(String agreeNum) {
		this.agreeNum = agreeNum;
	}

	@Override
	public String toString(){
		return "名称: " + name + "\n" 
				+ " 原价: " + originPrice + "\n"
				+ " 促销价: " + barginPrice + "\n" 
				+ " 喜爱度: " + agreeNum + "\n" 
				+ "购买链接" + url;
	}

}
