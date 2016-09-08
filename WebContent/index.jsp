<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
	<title>首页</title>
</head>
<script type="text/javascript">
    function setFormAction(){
	    var oForm = document.getElementsByTagName("form")[0];
	    var aOpt = document.getElementsByTagName("option");
	    

		if(aOpt[0].selected === true ){
			oForm.action="SearchInfoMoGu";
		}
		
		if(aOpt[1].selected === true ){
			oForm.action="SearchInfoDangDang";
		}

	    oForm.submit();
	}
</script>
<body>
    <div>
        <form name="upload" action=# method="post">
            <font>商品搜索：</font>
            <input name="name" type="text"/>
			<select autocomplete="off">
            	<option value="mogujie">蘑菇街</option>
            	<option value="dangdang">当当网</option>
            </select><br>
            <input type="submit" value="提交" onclick="setFormAction();">
        	<input type="reset" value="重置" style=" position: relative ; left:20px;">
        </form>
        <input name="name" type="button" value="查看收藏" onclick="window.location='collection.jsp'"/>
        <div>
        
        </div>
    </div>
    
</body>
</html>