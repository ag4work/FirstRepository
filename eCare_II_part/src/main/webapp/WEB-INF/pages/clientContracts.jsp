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

<div class="container-fluid">
    <div class="row">

        <br><br>
        <div class="col-md-11 main" style="position:relative; ">
            <table class="table table-striped">
                <h3> Пользователь</h3>
                ${user.name} ${user.lastname} ${user.birthday}
                <h3> Контракты пользователя</h3>
                <thead>
                <tr>
                    <th> Номер телефона </th>
                    <th> Баланс </th>
                    <th> Тариф</th>
                    <th> Статус</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach var="contract" items="${contractSet}">
                    <tr>
                        <td>
                                ${contract.phoneNumber}
                        </td>
                        <td>
                                ${contract.balance}
                        </td>
                        <td>
                                ${contract.tariffDTO.title}
                        </td>
                        <td>
                            <c:if test="${contract.blocked == false}">
                                Online
                            </c:if>
                            <c:if test="${contract.blocked == true}">
                                Заблокирован
                            </c:if>
                        </td>


                        <td>
                            <form class="formButton" action="contractEdit.sec" method="post">
                                <input type="hidden" name="contractId" value="${contract.contractId}"/>
                                <input type="submit" class="btn btn-link btn-xs" value="Редактировать контракт"/>
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
