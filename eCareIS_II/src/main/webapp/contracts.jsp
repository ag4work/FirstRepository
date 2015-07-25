<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 24.06.2015
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
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

<table border="1">

    <thead>
    <tr>
        <td> Номер телефона </td>
        <td> Баланс</td>
    </tr>

    </thead>
    <c:forEach var="contract" items="${contractList}">
        <tr>
            <td>
                ${contract.phoneNumber}
            </td>
            <td>
                ${contract.balance}
            </td>
        </tr>
    </c:forEach>
</table>

<%--<h3> Создать новый тариф </h3>--%>
<%--<form name="test" method="post" action="/addTariff">--%>
    <%--<p> Название--%>
        <%--<input type="text" name="title"></p>--%>
    <%--<p> Цена--%>
        <%--<input type="text" name="price"></p>--%>
    <%--<p><input type="submit" value="Отправить">--%>
        <%--<input type="reset" value="Очистить"></p>--%>
<%--</form>--%>
</body>
</html>
