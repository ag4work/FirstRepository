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
    <title>All user</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
</head>
<body>

<table border="1">
    <h3> All users</h3>
    <thead>
    <tr>
        <td> Имя </td>
        <td> Фамилия</td>
        <td> Дата рождения</td>
        <td> Пасспортные данные</td>
        <td> Адрес</td>
        <td> email</td>
        <td> пароль</td>
        <td> Роль</td>
        <td> Контрактов</td>
    </tr>

    </thead>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>
                    ${user.name}
            </td>
            <td>
                    ${user.lastname}
            </td>
            <td>
                    ${user.birthday}
            </td>
            <td>
                    ${user.passport}
            </td>
            <td>
                    ${user.address}
            </td>
            <td>
                    ${user.email}
            </td>

            <td>
                    ${user.password}
            </td>

            <td>
                    ${user.role}
            </td>

            <td>
                    <%--${user.getContracts().size()}--%>
            </td>


        </tr>
    </c:forEach>
</table>

<h3> Добавить пользователя </h3>
<form name="test" method="post" action="/addUser.sec">

    <p> Имя </p> <input type="text" name="name">
    <p> Фамилия</p> <input type="text" name="lastname">
    <p> Дата рождения</p> <input type="text" name="birthday">
    <p> Пасспортные данные</p> <input type="text" name="passport">
    <p> Адрес</p> <input type="text" name="address">
    <p> email</p> <input type="text" name="email">
    <p> пароль</p> <input type="text" name="password">
    <p> Роль</p> <input type="text" name="role">


    <p><input type="submit" value="Отправить">
        <input type="reset" value="Очистить"></p>
</form>
</body>
</html>
