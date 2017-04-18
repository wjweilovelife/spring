<%--
  Created by IntelliJ IDEA.
  User: pactera
  Date: 2017/4/14
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mq</title>
</head>
<body>
    <form action="/mq/send" method="POST">
        message:<input type="text" name="message" />
        <input type="submit" value="mq_submit" />
    </form>
</body>
</html>
