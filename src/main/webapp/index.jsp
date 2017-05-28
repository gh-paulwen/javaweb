<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>index</title>
<script type="text/javascript">
window.location.href=  "front/login.html";
</script>
</head>
<%
	String base = request.getContextPath();
%>
<body>
	<a href="<%=base%>/user?method=save&username=李&age=18">点击</a><br>
	
	<form action="<%=base%>/user" method="POST">
		<input type="hidden" name="method" value="save">
		<input name="username" value="大卫">
		<input name="age" value="19">
		<input type="submit">
	</form>
</body>
</html>