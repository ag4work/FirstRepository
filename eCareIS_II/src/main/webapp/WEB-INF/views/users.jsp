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


<%@ include file="headermenu.jsp" %>
<%@ include file="message.jsp" %>

<div class="container-fluid">
    <div class="row">

        <br><br>
        <div class="col-md-11 main" style="position:relative; ">
            <table class="table table-striped">
                <h3> All users</h3>
                <thead>
                <tr>
                    <th> Имя </td>
                    <th> Фамилия</td>
                    <th> Дата рождения</td>
                    <th> Паспортные <br>данные</td>
                    <th> Адрес</td>
                    <th> email</td>
                    <th> пароль</td>
                    <th> Роль</td>
                    <th> Кол-во <br> контрактов</td>
                </tr>

                </thead>
                <tbody>
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
                            <c:if test="${user.role == 0}">
                                Сотрудник
                            </c:if>
                            <c:if test="${user.role == 1}">
                                Клиент
                            </c:if>

                        </td>

                        <td>
                            <c:if test="${fn:length(user.contracts) gt 0}">
                                <a class="btn btn-info btn-xs"
                                   href="${pageContext.request.contextPath}/users/${user.userId}/contracts/">
                                        ${fn:length(user.contracts)}

                                <%--<form class="formButton" action="showUserContracts.sec" method="post">--%>
                                    <%--<input type="hidden" name="userId" value="${user.userId}"/>--%>
                                    <%--<input type="hidden" name="command" value="showUserContracts"/>--%>
                                    <%--<input type="submit" class="btn btn-link btn-xs" value="${fn:length(user.contracts)}"/>--%>
                                <%--</form>--%>
                            </c:if>

                        </td>
                        <td>
                            <form class="formButton" action="phonenumber_choosenew.sec" method="post">
                                <input type="hidden" name="id" value="${user.userId}"/>
                                <input type="submit" class="btn btn-link btn-xs" value="Добавить номер"/>
                            </form>
                        </td>


                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
