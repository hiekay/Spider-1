<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spider.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spider.sql.Mogu" %>
<%@ page import="com.spider.sql.moguoperations" %>
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
<% ArrayList<Clothes> clothesList = (ArrayList<Clothes>)request.getAttribute("cl");%>

            
            <%
                for(Clothes clothes:clothesList){
            %>
                
                <div class="show">
                	<img src="<%=clothes.getPic() %>" alt="<%=clothes.getName() %>"/>
                	<p>
	                	<span>商品名称：<%=clothes.getName() %></span><br />
	                	<span>商品原价：<%=clothes.getOriginPrice() %></span><br />
	                	<span>商品现价：<%=clothes.getBarginPrice() %></span><br />
	                	<span>喜爱度：<%=clothes.getAgreeNum() %></span><br />
	                	<span>购买链接：<%=clothes.getUrl() %></span>
	                </p>
	                	<form action="./moguoperations" method="post">
	                		<input type="hidden" name="name" value="<%=clothes.getName() %>"/>
	                		<input type="hidden" name="pic" value="<%=clothes.getPic() %>"/>
	                		<input type="hidden" name="originPrice" value="<%=clothes.getOriginPrice() %>"/>
	                		<input type="hidden" name="barginPrice" value="<%=clothes.getBarginPrice() %>"/>
	                		<input type="hidden" name="agreeNum" value="<%=clothes.getAgreeNum() %>"/>
	                		<input type="hidden" name="url" value="<%=clothes.getUrl() %>"/>
	                	   	<input type="submit" value="收藏" />
	                	</form>	
                </div>
                	

            <%
                }
            
            %>
            
</body>
</html>