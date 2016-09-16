package com.spider.sql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dangdangoperations extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        String publishor = request.getParameter("publishor");
        String time = request.getParameter("time");
        String url = request.getParameter("url");
        String image = request.getParameter("image");

        System.out.println("当当网");
		DangDang dangdang = new DangDang(name,price,author,publishor,time,url,image);
		
		insert(dangdang);
		
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
	
	private static Connection getConn() {			//���и���
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/spider";
	    String username = "root";
	    String password = "";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,���ض�Ӧ����
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	 }
	
	public static int insert(DangDang dangdang) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into dangdang (name,price,author,publishor,"
	    		+ "time,url,image) values(?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, dangdang.getName());
	        pstmt.setString(2, dangdang.getPrice());
	        pstmt.setString(3, dangdang.getAuthor());
	        pstmt.setString(4, dangdang.getPublishor());
	        pstmt.setString(5, dangdang.getTime());
	        pstmt.setString(6, dangdang.getUrl());
	        pstmt.setString(7, dangdang.getImage());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public static Integer select() {
	    Connection conn = getConn();
	    String sql = "select * from dangdang";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	            for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	                if ((i == 2) && (rs.getString(i).length() < 8)) {
	                    System.out.print("\t");
	                }
	             }
	            System.out.println("");
	        }
	            System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static int delete(String name) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from dangdang where name='" + name + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
}