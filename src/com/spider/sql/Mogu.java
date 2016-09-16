package com.spider.sql;

public class Mogu {
	
	private String Id;
	private String name;     //��Ʒ��
	private String pic;     //ͼƬ
	private String originPrice;    //ԭ��
	private String barginPrice;   //������
	private String agreeNum;     //ϲ����	
	private String url;      //��������
	
	public Mogu(String name, String pic, String originPrice,
			String barginPrice, String agreeNum, String url) {
		this.Id = null; //default
		this.name = name;
		this.pic = pic;
		this.originPrice = originPrice;
		this.barginPrice = barginPrice;
		this.agreeNum = agreeNum;
		this.url = url;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getOriginPrice() {
		return originPrice;
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

	public String getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(String agreeNum) {
		this.agreeNum = agreeNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
