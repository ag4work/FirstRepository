<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 24.06.2015
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<% if (request.getParameter("BadEmailPassword") != null) {%>
<p> Authorization failed.</p> <br>
<%}%>
<%=  request.getParameter("BadEmailPassword")%>
<p > hello</p>
<form action="/login" method="get">
  email <input name="email"> <br>
  Пароль <input name="password"> <br>
  <input type="submit" value="Sign in">
</form>
</body>
</html>
