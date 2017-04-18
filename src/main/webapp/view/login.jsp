<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h4>Login Page</h4>
	
	<form action="/shiro/login" method="POST">
		username: <input type="text" name="username"/>
		<br><br>
		
		password: <input type="password" name="password"/>
		<br><br>
		rememberme:<input type="checkbox" name="remember" />
		
		<input type="submit" value="Submit"/>
	</form>
</body>
</html>