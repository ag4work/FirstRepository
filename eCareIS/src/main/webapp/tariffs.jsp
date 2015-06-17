<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 11.06.2015
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Tariff" %>

<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>


<html>
<head>
  <title>Tariffs's page</title>
  <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
</head>
<body>

<%--<% List<Train> list = (ArrayList<Train>)request.getAttribute("tr");--%>
<%--for (Train s : list){--%>
<%--out.println(s);--%>
<%--}--%>
<%--%>--%>


<table border="1">

  <%--<c:forEach var="i" begin="1" end="10">--%>
  <%--<c:out value="${i}" />--%>
  <%--</c:forEach>--%>
  <thead>
  <tr>
    <td> Название тарифа </td>
    <td> Стоимость </td>
  </tr>

  </thead>
  <c:forEach  var="tariff" items="${tariffList}">
    <tr>
      <td>
          ${tariff.title}
      </td>
      <td>
          ${tariff.price}
      </td>
    </tr>
  </c:forEach>
</table>

<h3> Создать новый тариф </h3>
<form name="test" method="post" action="/addTariff">
  <p> Название
    <input type="text" name="title" > </p>
  <p> Цена
    <input type="text" name="price" > </p>
  <p><input type="submit" value="Отправить">
    <input type="reset" value="Очистить"></p>
</form>

</body>
</html>
