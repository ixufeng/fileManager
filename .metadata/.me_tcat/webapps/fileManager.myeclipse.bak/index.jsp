<%@page import="java.awt.print.Printable"%>
<%@page import="file.MyFile"%>
<%@page import="sql.SqlHelper"%>
<%@page import="java.sql.Connection"%>
<%@page import="sql.SqlConnect"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
 <head>
	<meta charset="utf-8">
	<title>登录</title>
	<meta name="description" content="slick Login">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="placeholder.js"></script> 
</head>
 <body>
	<form id="slick-login" method="post" action="LoginHandle.do">
		<label for="username">username</label>
		<input type="text" name="username" class="placeholder" placeholder="用户名" />
		<label for="password">password</label>
		<input type="password" name="password" class="placeholder" placeholder="密码" />
		<input type="submit" value="登录" />
	</form>
</body>
 
</html>
