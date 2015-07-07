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
<%--<div class="container-fluid">--%>
<%--<div class="row">--%>
<%--<div class="col-md-5 main" style="position:relative; ">--%>
<%--<h2> Изменение списка возможных опций на тарифе ${tariff.title} </h2>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<div class="container-fluid">
  <div class="row">
    <div class="col-md-3 main" style="position:relative; ">
      <div>
        <h3> Информация о контракте:</h3>
        ${contract.userDTO.name} ${contract.userDTO.lastname} <br>
        ${contract.phoneNumber} <br>
        ${contract.balance}<br>
        Тариф: "${contract.tariffDTO.title}" <br>
        Список опций:
      </div>
      <div>
        <h3> Корзина:</h3>
        Пока ничего не выбрано

      </div>

    </div>


    <div class="col-md-8 main" style="position:relative; ">
      <div>
        <h3>Выберете тариф:</h3>
        <form role="form" action="" method="post">
          <div class="form-group">
            <label for="sel1">Выберите тариф из списка:</label>
            <select class="form-control" name="tariffs" id="sel1">
              <c:forEach var="tariff" items="${tariffs}">
                <option value="${tariff.tariffId}">${tariff.title}, ${tariff.price} руб. </option>
              </c:forEach>
            </select>
          </div>

          <%--<input type="hidden" name="userId" value="${user.userId}"/>--%>

          <div class="control-buttons">
            <button type="submit" class="btn btn-primary">Применить</button>
          </div>
        </form>
      </div>

      <table class="table table-striped">
        <h3> Опции, возможные для подключения на тарифе</h3>
        <thead>
        <tr>
          <th> Название опции </th>
          <th> Ежемесячная плата </th>
          <th> Стоимость подключения </th>
          <th> Зависимые опции </th>
          <th> Несовместимые опции </th>
          <th> Операция </th>
        </tr>
        </thead>

        <tbody>
        <c:forEach  var="option" items="${chosenTariffOptions}">
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
              <c:forEach  var="reqQption" items="${option.dependentOption}">
                ${reqQption.title} <br>
              </c:forEach>
            </td>

            <td>
              <c:forEach  var="reqQption" items="${option.requiredOption}">
                ${reqQption.title} <br>
              </c:forEach>
            </td>

            <td>
              <form class="formButton" action="TariffPossibleOptionsEdit.sec" method="post">
                <input type="hidden" name="tariffId" value="${tariff.tariffId}"/>
                <input type="hidden" name="optionId" value="${option.optionId}"/>
                <input type="hidden" name="command" value="remove"/>
                <input type="submit" class="btn btn-link btn-xs" value="В корзину"/>
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

