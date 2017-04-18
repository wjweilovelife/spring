<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h4>List Page</h4>
	
	Welcome: <shiro:principal></shiro:principal>
	
	<shiro:hasRole name="admin">
	<br><br>
	<a href="/view/admin.jsp">Admin Page</a>
	</shiro:hasRole>
	
	<shiro:hasRole name="user">
	<br><br>
	<a href="/view/user.jsp">User Page</a>
	</shiro:hasRole>
	
	<br><br>
	<a href="/shiro/testAnnotation">Test ShiroAnnotation</a>
	
	<br><br>
	<a href="/logout">Logout</a>

</body>
</html>