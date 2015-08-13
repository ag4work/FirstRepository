<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
</head>


<body>


<%@ include file="headermenu.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-11 main" style="position:relative; ">
                <sf:form role="form" action="${pageContext.request.contextPath}/app/contracts/search" modelAttribute="searchNumberForm" method="post">
                    <div class="form-group">
                        <sf:label path="number" for="phoneNumber">Найти контракт:</sf:label>
                        <sf:input path="number" name="phoneNumber" type="text" class="form-control" id="phoneNumber" placeholder="Введите номер (10 цифр)"/>
                        <sf:errors path="number" cssClass="errorlabel" />
                    </div>

                    <div class="control-buttons">
                        <button type="submit" class="btn btn-primary">Найти</button>
                    </div>
                </sf:form>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">

        <br>

        <div class="col-md-11 main" style="position:relative; ">
            <h3> Контракты</h3>
            <ul class="pagination">
                <c:forEach begin="1" end="${numOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="disabled"><a href="#">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/app/contracts/page/${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
            <table class="table table-striped">

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
                                +7 (${fn:substring(contract.phoneNumber,0 ,3)}) ${fn:substring(contract.phoneNumber,3 ,6)}-${fn:substring(contract.phoneNumber,6 ,8)}-${fn:substring(contract.phoneNumber,8 ,10)}
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
                                    <input type="hidden" name="page" value="${currentPage}"/>
                                    <input type="submit" class="btn btn-danger btn-xs" value="Блокировать"/>

                                </form>
                            </c:if>
                            <c:if test="${contract.blocked==true}">
                                <form class="formButton" action="${pageContext.request.contextPath}/app/contractBlockStatusEdit" method="post">
                                    <input type="hidden" name="contractId" value="${contract.contractId}"/>
                                    <input type="hidden" name="command" value="unblock"/>
                                    <input type="hidden" name="page" value="${currentPage}"/>
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
