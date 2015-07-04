<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <h3> ${user.birthday} </h1>
        </div>

        <div class="col-md-3">
            <h3>Выберете номер телефона</h2>
            <form role="form" action="addContract.sec" method="post">
                <div class="form-group">
                    <label for="sel1">Choose phonenumber from the list:</label>
                    <select class="form-control" name="phoneNumber" id="sel1">
                        <c:forEach var="phonenumber" items="${phonenumbersList}">
                                    <option value="${phonenumber}">${phonenumber}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                        <label for="initialbalance">Первоначальный взнос на счет:</label>
                        <input name="initialbalance" type="text" class="form-control" id="initialbalance" placeholder="Введите сумму">
                </div>
                <input type="hidden" name="userId" value="${user.userId}"/>

                <div class="control-buttons">
                    <button type="submit" class="btn btn-primary">Добавить данные</button>
                </div>
            </form>
        </div>

        <div class="col-md-3" style="position:relative; ">
        </div>


    </div>
</div>

</body>
</html>
