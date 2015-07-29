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
        <div class="col-md-11 main" style="position:relative; ">
                <form role="form" action="ContractSearch.sec" method="post">
                    <div class="form-group">
                        <label for="phoneNumber">Найти контракт:</label>
                        <input name="phoneNumber" type="text" class="form-control" id="phoneNumber" placeholder="Введите номер (10 цифр)">
                    </div>

                    <div class="control-buttons">
                        <button type="submit" class="btn btn-primary">Найти</button>
                    </div>
                </form>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">

        <br><br>
        <div class="col-md-11 main" style="position:relative; ">
            <table class="table table-striped">
                <h3> Контракты</h3>
                <thead>
                <tr>
                    <th> Номер телефона </th>
                    <th> ФИО </th>
                    <th> Тариф </th>
                    <th> Баланс</th>
                    <th> Статус</th>
                    <th> Действие</th>
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

                        <td>
                                ${contract.balance}
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
                            <c:if test="${contract.blocked==false}">
                                <form class="formButton" action="${pageContext.request.contextPath}/app/contractBlockStatusEdit" method="post">
                                    <input type="hidden" name="contractId" value="${contract.contractId}"/>
                                    <input type="hidden" name="command" value="block"/>
                                    <input type="submit" class="btn btn-danger btn-xs" value="Блокировать"/>

                                </form>
                            </c:if>
                            <c:if test="${contract.blocked==true}">
                                <form class="formButton" action="${pageContext.request.contextPath}/app/contractBlockStatusEdit" method="post">
                                    <input type="hidden" name="contractId" value="${contract.contractId}"/>
                                    <input type="hidden" name="command" value="unblock"/>
                                    <input type="submit" class="btn btn-success btn-xs" value="Разблокировать"/>
                                </form>

                            </c:if>
                            <form class="formButton" action="${pageContext.request.contextPath}/app/contractEdit" method="post">
                                <input type="hidden" name="contractId" value="${contract.contractId}"/>
                                <input type="hidden" name="tariffId" value="-1"/>
                                <input type="submit" class="btn btn-info btn-xs" value="Редактировать контракт"/>
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
