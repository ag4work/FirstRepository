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
        <div class="col-md-3" style="position:relative; ">
            <H2> ${user.name} ${user.lastname} </H2>
            <h3> ${user.birthday} </h3>
        </div>

        <div class="col-md-3">
            <h3>Выберете номер телефона</h3>
            <sf:form role="form" action="${pageContext.request.contextPath}/app/contracts/add" modelAttribute="addNumberToContractForm" method="post">
                <div class="form-group">
                    <sf:label path="phoneNumber" for="sel1">Choose phonenumber from the list:</sf:label>
                    <sf:select path="phoneNumber" class="form-control" name="phoneNumber" id="sel1">
                        <c:forEach var="phonenumber" items="${phonenumbersList}">
                                    <sf:option value="${phonenumber}">
                                        +7 (${fn:substring(phonenumber,0 ,3)}) ${fn:substring(phonenumber,3 ,6)}-${fn:substring(phonenumber,6 ,8)}-${fn:substring(phonenumber,8 ,10)}
                                    </sf:option>
                        </c:forEach>
                    </sf:select>
                    <sf:errors  path="phoneNumber" cssClass="errorlabel" />
                </div>
                <div class="form-group">
                        <sf:label path="initialBalance" for="initialbalance">Первоначальный взнос на счет:</sf:label>
                        <sf:input path="initialBalance" name="initialbalance" type="text" class="form-control" id="initialbalance" placeholder="Введите сумму"/>
                        <sf:errors path="initialBalance" cssClass="errorlabel" />
                </div>
                <input type="hidden" name="userId" value="${user.userId}"/>

                <div class="control-buttons">
                    <button type="submit" class="btn btn-primary">Добавить данные</button>
                </div>
            </sf:form>
        </div>

        <div class="col-md-3" style="position:relative; ">
        </div>


    </div>
</div>

</body>
</html>
