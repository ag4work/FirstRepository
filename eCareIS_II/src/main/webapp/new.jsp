<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<link rel="stylesheet" href="css/mystyles.css">
<div class="container">
  <div class="divCenterleft">
    <div class = "row">
      <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/loginStaff" role="button"> Вход для сотрудника</a>
      <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/loginClient" role="button"> Вход для клиента</a>
    </div>
  </div>






</div>
</body>
</html>
