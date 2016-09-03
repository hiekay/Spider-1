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
    <div>
        <table class="st">
            <tr><td>商品名称</td><td>价格</td><td>作者</td><td>出版社</td><td>出版时间</td><td>购买链接</td><td>图片展示</td></tr>
            <%
                for(Book book:booklist){
            %>
                <tr><td><div><%=book.getName() %></div></td>
                	<td><div><%=book.getPrice() %></div></td>
                    <td><div><%=book.getAuthor() %></div></td>
                    <td><div><%=book.getPublishor() %></div></td>
                	<td><div><%=book.getTime() %></div></td>
                	<td><div><a href="<%=book.getUrl() %>">购买链接</a></div></td>
                	<td><div><img src="<%=book.getImage() %>"></div></td>
                </tr>
            <%
                }
            
            %>
            
        </table>
    </div>

</body>
</html>