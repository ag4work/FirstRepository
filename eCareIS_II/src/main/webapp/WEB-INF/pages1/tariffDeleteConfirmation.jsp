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
<c:if test="${empty tariffDeleted}">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5 main" style="position:relative; ">


                <h4> Вы действительно хотите удалить тариф: "${tariff.title}" ? </h4>
                <h5> Список контрактов, подключенных на данный тариф, приведен ниже. При
                    удалении тарифа они будут переведены на тариф "Базовый".
                </h5>
                <form role="form" action="deleteTariff.sec" method="post">
                    <div class="control-buttons">
                        <button type="submit" class="btn btn-primary">Да, удалить.</button>
                        <input type="hidden" name="command" value="deleteConfirmed"/>
                        <input type="hidden" name="tariffId" value="${tariff.tariffId}"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>

<div class="container-fluid">
    <div class="row">

        <br><br>
        <div class="col-md-7 main" style="position:relative; ">
            <table class="table table-striped">
                <%@ include file="message.jsp" %>
                <h3> Контракты</h3>
                <thead>
                <tr>
                    <th> Номер телефона </th>
                    <th> ФИО </th>
                    <th> Тариф </th>
                </tr>

                </thead>
                <tbody>
                <c:forEach var="contract" items="${contractSet}">
                    <tr>
                        <td>
                                ${contract.phoneNumber}
                        </td>

                        <td>
                                ${contract.userDTO.name} ${contract.userDTO.lastname}
                        </td>

                        <td>
                                ${contract.tariffDTO.title}
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
