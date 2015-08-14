<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 28.07.2015
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  <title></title>
</head>
<body>
  <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/loginStaff"> Вход для сотрудника</a>
  <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/loginClient"> Вход для клиента</a>

</body>
</html>
