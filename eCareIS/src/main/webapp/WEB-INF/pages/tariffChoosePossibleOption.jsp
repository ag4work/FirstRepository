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
    <div class="col-md-5 main" style="position:relative; ">
      <h2> Изменение списка возможных опций на тарифе ${tariff.title} </h2>
    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="row">

    <div class="col-md-5 main" style="position:relative; ">
      <table class="table table-striped">
        <h3> Опции, возможные для подключения на тарифе</h3>
        <thead>
        <tr>
          <th> Название опции </th>
          <th> Ежемесячная плата </th>
          <th> Стоимость подключения </th>
          <th> Операция </th>
        </tr>
        </thead>

        <tbody>
        <c:forEach  var="option" items="${tariffPossibleOptions}">
          <tr>

            <td>
                ${option.title}
            </td>

            <td>
                ${option.monthlyCost}
            </td>

            <td>
                ${option.activationCharge}
            </td>

            <td>
              <form class="formButton" action="TariffPossibleOptionsEdit.sec" method="post">
                <input type="hidden" name="tariffId" value="${tariff.tariffId}"/>
                <input type="hidden" name="optionId" value="${option.optionId}"/>
                <input type="hidden" name="command" value="remove"/>
                <input type="submit" class="btn btn-link btn-xs" value="Убрать"/>
              </form>
            </td>

          </tr>
        </c:forEach>
        </tbody>

      </table>
      <table class="table table-striped">
        <h3> Все остальные опции</h3>
        <thead>
        <tr>
          <th> Название опции </th>
          <th> Ежемесячная плата </th>
          <th> Стоимость подключения </th>
          <th> Операция </th>
        </tr>
        </thead>

        <tbody>
        <c:forEach  var="option" items="${allOtherOptions}">
          <tr>

            <td>
                ${option.title}
            </td>

            <td>
                ${option.monthlyCost}
            </td>

            <td>
                ${option.activationCharge}
            </td>

            <td>
              <form class="formButton" action="TariffPossibleOptionsEdit.sec" method="post">
                <input type="hidden" name="tariffId" value="${tariff.tariffId}"/>
                <input type="hidden" name="optionId" value="${option.optionId}"/>
                <input type="hidden" name="command" value="add"/>
                <input type="submit" class="btn btn-link btn-xs" value="Добавить"/>
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
