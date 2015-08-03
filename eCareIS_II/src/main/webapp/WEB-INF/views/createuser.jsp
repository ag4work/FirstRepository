<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


        <div class="col-md-6 col-md-offset-3 main" style="position:relative; ">

            <%@ include file="message.jsp" %>

            <h2>Зарегестрировать нового пользователя</h2> <br>

            <sf:form role="form" method="post" modelAttribute="newUserForm" action="${pageContext.request.contextPath}/app/users/add">
                <div class="form-group">
                    <sf:label path="name" for="name">Имя:</sf:label>
                    <sf:input path="name" name="name" type="text" class="form-control" id="name" placeholder="Введите имя"/>
                    <sf:errors path="name" cssClass="error" />
                </div>
                <div class="form-group">
                    <sf:label path="lastname" for="lastname">Фамилия:</sf:label>
                    <sf:input path="lastname" name="lastname" type="text" class="form-control" id="lastname" placeholder="Введите фамилию"/>
                    <sf:errors path="lastname" cssClass="error" />
                </div>
                <div class="form-group">
                    <sf:label path="birthday" for="birthday">Дата рождения:</sf:label>
                    <sf:input path="birthday" name="birthday" type="text" class="form-control" id="birthday" placeholder="DD-MM-YYYY"/>
                    <sf:errors path="birthday" cssClass="error" />
                </div>

                <div class="form-group">
                    <sf:label path="passport" for="passport">Паспортные данные:</sf:label>
                    <sf:input path="passport" name="passport" type="text" class="form-control" id="passport" placeholder="Введите паспортные данные"/>
                    <sf:errors path="passport" cssClass="error" />
                </div>

                <div class="form-group">
                    <sf:label path="address" for="address">Адрес:</sf:label>
                    <sf:input path="address" name="address" type="text" class="form-control" id="address" placeholder="Введите адрес"/>
                    <sf:errors path="address" cssClass="error" />
                </div>

                <div class="form-group">
                    <sf:label path="email" for="email">Email:</sf:label>
                    <sf:input path="email" name="email" type="text" class="form-control" id="email" placeholder="Введите фамилию"/>
                    <sf:errors path="email" cssClass="error" />
                </div>

                <label for="role">Категория пользователя:</label>
                <sf:select path="role" name="role" class="form-control" id="userType">
                    <sf:option value="1">Клиент</sf:option>
                    <sf:option value="0">Сотрудник компании</sf:option>
                </sf:select>
                <br>

                <div class="form-group">
                    <sf:label path="password" for="pwd">Password:</sf:label>
                    <sf:input path="password" name="password" type="password" class="form-control" id="pwd" placeholder="Enter password"/>
                    <sf:errors path="password" cssClass="error" />
                </div>

                <div class="control-buttons">
                    <button type="submit" class="btn btn-primary">Добавить данные</button>
                </div>

            </sf:form>
        </div>
    </div>
</div>

</body>
</html>
