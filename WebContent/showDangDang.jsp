<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spider.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
	<title>展示页面</title>
	<link rel="stylesheet" type="text/css" href="css/show.css"/>
	
	<script type="text/javascript">     <!--实现弹出窗口 -->
      function formSubmit(){
		    if(confirm('是否确定查询此信息？')){
		        document.getElementByClass("st").submit();
		
		    }
		
		}
      
    </script>
</head>
<body>
<% ArrayList<Book> booklist = (ArrayList<Book>)request.getAttribute("bl");%>
            <%
                for(Book book:booklist){
            %>
                <div class="show">
                	<img src="<%=book.getImage() %>" alt="<%=book.getName() %>"/>
                	<p>
	                	<span>书籍名称：<%=book.getName() %></span><br />
	                	<span>书籍价格：<%=book.getPrice() %></span><br />
	                	<span>作者：<%=book.getAuthor() %></span><br />
	                	<span>出版社：<%=book.getPublishor() %></span><br />
	                	<span>出版时间：<%=book.getTime() %></span><br />
	                	<span>购买链接：<%=book.getUrl() %></span>

	                </p>
	                <form action="./dangdangoperations" method="post">
                		<input type="hidden" name="name" value="<%=book.getName() %>"/>
                		<input type="hidden" name="price" value="<%=book.getPrice() %>"/>
                		<input type="hidden" name="author" value="<%=book.getAuthor() %>"/>
                		<input type="hidden" name="publishor" value="<%=book.getPublishor() %>"/>
                		<input type="hidden" name="time" value="<%=book.getTime() %>"/>
                		<input type="hidden" name="url" value="<%=book.getUrl() %>"/>
                		<input type="hidden" name="image" value="<%=book.getImage() %>"/>
                	   	<input type="submit" value="收藏" />
                	</form>
                </div>
            <%
                }
            
            %>
</body>
</html>