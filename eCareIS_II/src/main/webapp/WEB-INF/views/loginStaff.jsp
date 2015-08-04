<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 24.06.2015
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

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
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style1.css" media="all">
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

  <%@ include file="../pages/message.jsp" %>
  <div class = "row">

    <div class="col-md-4 col-md-offset-4">
      <div class="editing-form">
        <ul class="nav nav-tabs">
          <li class="active"><a data-toggle="tab" href="#home">Вход для сотрудников</a></li>
        </ul>

        <br>
        <div class="tab-content">

          <div id="home" class="tab-pane fade in active">

            <sf:form role="form" method="post" action="${pageContext.request.contextPath}/staffLogin" modelAttribute="emailPasswForm">
              <div class="form-group">
                <sf:label path="email" for="email">Email:</sf:label>
                <sf:input path="email" name="email" type="email" class="form-control" id="email" placeholder="Введите e-mail"/>
                <sf:errors path="email" cssClass="errorlabel" />
              </div>
              <div class="form-group">
                <sf:label path="pass" for="pwd2">Password:</sf:label>
                <sf:input path="pass" name="psw2" type="password" class="form-control" id="psw2" placeholder="Введите пароль"/>
                <sf:errors path="pass" cssClass="errorlabel" />
              </div>
              <%--<input type="hidden" name="command" value="staffLogin"/>--%>
              <button type="submit" class="btn btn-default" id="dd">Submit</button>
            </sf:form>
          </div>

        </div>

      </div>

    </div>
  </div>

</div>


</body>
</html>
