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


        <div class="col-md-6 col-md-offset-3 main" style="position:relative; ">

            <%@ include file="message.jsp" %>

            <h2>Зарегестрировать нового пользователя</h2> <br>

            <form role="form" method="post" action="/addUser.sec">
                <div class="form-group">
                    <label for="name">Имя:</label>
                    <input name="name" type="text" class="form-control" id="name" placeholder="Введите имя">
                </div>
                <div class="form-group">
                    <label for="lastname">Фамилия:</label>
                    <input name="lastname" type="text" class="form-control" id="lastname" placeholder="Введите фамилию">
                </div>
                <div class="form-group">
                    <label for="birthday">Дата рождения:</label>
                    <input name="birthday" type="text" class="form-control" id="birthday" placeholder="YYYY-MM-DD">
                </div>

                <div class="form-group">
                    <label for="passport">Паспортные данные:</label>
                    <input name="passport" type="text" class="form-control" id="passport" placeholder="Введите паспортные данные">
                </div>

                <div class="form-group">
                    <label for="address">Адрес:</label>
                    <input name="address" type="text" class="form-control" id="address" placeholder="Введите адрес">
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input name="email" type="text" class="form-control" id="email" placeholder="Введите фамилию">
                </div>

                <label for="usertype">Категория пользователя:</label>
                <select name="usertype" class="form-control" id="userType">
                    <option value="1">Клиент</option>
                    <option value="0">Сотрудник компании</option>
                </select>
                <br>

                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input name="password" type="password" class="form-control" id="pwd" placeholder="Enter password">
                </div>
                <div class="control-buttons">
                    <button type="submit" class="btn btn-primary">Добавить данные</button>
                </div>


            </form>
        </div>
    </div>
</div>

</body>
</html>
