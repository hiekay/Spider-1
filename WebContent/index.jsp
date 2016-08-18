<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
	<title>首页</title>
</head>
<body>
    <div>
        <form name="upload"action="SearchInfo" method="post">
            <font>当当网商品搜索：</font>
            <input name="name" type="text"/><br>
            <input type="submit" value="提交" onclick="formSubmit();">
        	<input type="reset" value="重置" style=" position: relative ; left:20px;">
        </form>
    </div>
</body>
</html>