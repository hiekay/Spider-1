package com.spider.bean;

public class Book {
	private String name;     //书名
	private String price;    //价格
	private String author;   //作者
	private String publishor;//出版社
	private String time;     //出版时间
	
	public Book(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishor() {
		return publishor;
	}
	public void setPublishor(String publishor) {
		this.publishor = publishor;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
	@Override
	public String toString(){
		return "name: " + name + " price: " + price + " author: "
				+ author + " publishor: " + publishor + " time: "
				+ time;
	}
	
	

}
