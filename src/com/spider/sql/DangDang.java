package com.spider.sql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

final class DangDang{
        private String Id;
        private String name;
        private String price;
        private String author;
        private String publishor;
        private String time;
        private String url;
        private String image;

        DangDang(String name, String price, String author, String publishor, String time, String url, String image) {
            this.Id = null; //default
            this.name = name;
            this.price = price;
            this.author = author;
            this.publishor = publishor;
            this.time = time;
            this.url = url;
            this.image = image;
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

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}


		
}