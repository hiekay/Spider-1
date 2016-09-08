<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<html>
  <head> 
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查看收藏</title>
    <link rel="stylesheet" type="text/css" href="css/show.css"/>
  </head>
  <body>
  <%
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
	  String sql = "select * from dangdang";
      Statement stat = conn.createStatement();
      ResultSet rs = stat.executeQuery(sql); 
  %>
      <h1>当当网：</h1>
  <%
      while(rs.next()){
  %>
    	<div class="show">
      	<img src="<%=rs.getString(8) %>" alt="<%=rs.getString(2) %>"/>
      	<p>
          	<span>书籍名称：<%=rs.getString(2) %></span><br />
          	<span>书籍价格：<%=rs.getString(3) %></span><br />
          	<span>作者：<%=rs.getString(4) %></span><br />
          	<span>出版社：<%=rs.getString(5) %></span><br />
          	<span>出版时间：<%=rs.getString(6) %></span><br />
          	<span>购买链接：<%=rs.getString(7) %></span>

         </p>
      </div> 
  <%
      }
  %>
  	<h1>蘑菇街：</h1>
  <%
  	sql = "select * from mogu";
  	stat = conn.createStatement();
  	rs = stat.executeQuery(sql); 
    while(rs.next()){
  %>
    <div class="show">
     	<img src="<%=rs.getString(3) %>" alt="<%=rs.getString(2) %>"/>
      	<p>
          	<span>商品名称：<%=rs.getString(2) %></span><br />
          	<span>商品原价：<%=rs.getString(4) %></span><br />
          	<span>商品现价：<%=rs.getString(5) %></span><br />
          	<span>喜爱度：<%=rs.getString(6) %></span><br />
          	<span>购买链接：<%=rs.getString(7) %></span>
          </p>
      </div> 
   
   <%
      }
      if(rs != null){
         rs.close();
      }
      if(stat!=null){
         stat.close();
      }
      if(conn!=null){
         conn.close();
      } 
   %>
   <input type="button" value="返回首页" style="display:block;width:100px;height:60px;margin:0 auto;" onclick="window.location='index.jsp'"/>
  </body>
</html>