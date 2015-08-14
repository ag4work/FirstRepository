<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 24.06.2015
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%--<html lang="en">--%>
<head>
  <title>eCare</title>
  <meta charset="utf-8">
  <meta content="text/html; charset=utf-8" http-equiv="Content-Type">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>


<body>
<%--<% if (request.getParameter("BadEmailPassword") != null) {%>--%>
<%--<p> Authorization failed.</p> <br>--%>
<%--<%}%>--%>
<%--<%=  request.getParameter("BadEmailPassword")%>--%>
<%--<p > hello</p>--%>
<%--<form action="/login" method="get">--%>
<%--email <input name="email"> <br>--%>
<%--Пароль <input name="password"> <br>--%>
<%--<input type="submit" value="Sign in">--%>
<%--</form>--%>

<div class="container">

    <br><br><br><br><br><br>

  <%@ include file="WEB-INF/views/message.jsp" %>
  <div class = "row">
    <div class="col-md-4 col-md-offset-4">
      <h2> Что-то пошло не так :( Вскоре проблема будет устранена. </h2>
    </div>
  </div>

</div>


</body>
</html>
