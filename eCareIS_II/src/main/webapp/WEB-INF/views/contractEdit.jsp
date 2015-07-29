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
<%-- 0  means that is --%>
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
    <%@ include file="message.jsp" %>
    <div class="col-md-4 main" style="position:relative; ">
      <div>
        <h3> Контракт:</h3>
        <h4>${contract.userDTO.name} ${contract.userDTO.lastname} <br></h4>
        <h4> Номер телефона: ${contract.phoneNumber} <br> </h4>
        <h4> Баланс: ${contract.balance} руб. <br> </h4>
        <h4> Тариф: "${contract.tariffDTO.title}" <br> </h4>

        <%--<ul class="list-group">--%>
        <%--<c:forEach  var="contractOption" items="${contract.chosenOption}">--%>
        <%--<li class="list-group-item">${contractOption.title}</li>--%>
        <%--</c:forEach>--%>
        <%--</ul>--%>


      </div>

      <div>
        <table class="table table-striped">
          <h4> Список подключенных к контракту опций: </h4>
          <thead>
          <tr>
            <th> Опция </th>
            <th> Зависимые опции </th>
            <th> Операция </th>
          </tr>
          </thead>

          <tbody>
          <c:forEach  var="option" items="${contractOptions}">
            <tr>

              <td>
                  ${option.title}
              </td>

              <td>
                <c:forEach  var="depOption" items="${option.dependentOption}">
                  ${depOption.title} <br>
                </c:forEach>
              </td>

              <td>
                <form class="formButton" action="${pageContext.request.contextPath}/app/RemoveOptionFromContract" method="post">
                  <input type="hidden" name="optionId" value="${option.optionId}"/>
                  <input type="hidden" name="contractId" value="${contract.contractId}"/>
                  <input type="hidden" name="tariffId" value="${tariffs.get(0).tariffId}"/>
                  <input type="hidden" name="command" value="remove"/>
                  <input type="submit" class="btn btn-link btn-xs" value="Отключить"/>
                </form>
              </td>

            </tr>
          </c:forEach>
          </tbody>

        </table>

      </div>




      <div>
        <h3> Корзина:</h3>
        <c:if test="${!empty cart}">
          <h4> Тариф: "${cart.tariffDTO.title}" <br> </h4>
          <h4> Список опций: </h4>

          <ul class="list-group">
            <c:forEach  var="optionInCart" items="${cart.optionDTOset}">
              <li class="list-group-item">${optionInCart.title}, ${optionInCart.activationCharge} руб.</li>
            </c:forEach>
          </ul>

          <h4> Всего к оплате: ${cart.getTotalPayment() } руб. </h4>

          <div>
            <form role="form" method="post" action="${pageContext.request.contextPath}/app/PayForCart">
              <div class="control-buttons">
                <button type="submit" class="btn btn-primary">Оплатить</button>
              </div>
              <input type="hidden" name="contractId" value="${contract.contractId}"/>
            </form>

            <form role="form" method="post" action="">
              <div class="control-buttons">
                <button type="submit" class="btn btn-primary">Сохранить договор</button>
              </div>
              <input type="hidden" name="contractId" value="${contract.contractId}"/>
            </form>

          </div>

        </c:if>
        <c:if test="${empty cart}">
          <h4> -- пусто --</h4>
        </c:if>


      </div>

    </div>


    <div class="col-md-8 main" style="position:relative; ">
      <div>
        <h3>Выберете тариф:</h3>

        <form name=frmTest action="${pageContext.request.contextPath}/app/contractEdit" method=POST>
          <label for="sel1">Выберите тариф из списка:</label>
          <select class="form-control" name="tariffId" id="sel1" onChange="frmTest.submit();">
            <c:forEach var="tariff" items="${tariffs}">
              <option value="${tariff.tariffId}"> ${tariff.title}, ${tariff.price} руб. </option>
            </c:forEach>
          </select>
          <input type="hidden" name="contractId" value="${contract.contractId}"/>
        </form>


        <%--<div class="control-buttons">--%>
        <%--<button type="submit" class="btn btn-primary">Применить</button>--%>
        <%--</div>--%>
      </div>

      <div>
        <table class="table table-striped">
          <h3> Опции, возможные для подключения на тарифе</h3>
          <thead>
          <tr>
            <th> Название опции </th>
            <th> Ежемесячная плата </th>
            <th> Стоимость подключения </th>
            <th> Требующиеся опции </th>
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
                <c:forEach  var="reqOption" items="${option.requiredOption}">
                  ${reqOption.title} <br>
                </c:forEach>
              </td>

              <td>
                <c:forEach  var="inconsistOption" items="${option.inconsistentOption}">
                  ${inconsistOption.title} <br>
                </c:forEach>
              </td>

              <td>
                <form class="formButton" action="${pageContext.request.contextPath}/app/addTariffAndOptionToCart" method="post">
                  <input type="hidden" name="tariffId" value="${tariffs.get(0).tariffId}"/>
                  <input type="hidden" name="optionId" value="${option.optionId}"/>
                  <input type="hidden" name="contractId" value="${contract.contractId}"/>
                  <input type="hidden" name="command" value="remove"/>
                  <input type="submit" class="btn btn-info btn-xs" value="В корзину"/>
                </form>
              </td>

            </tr>
          </c:forEach>
          </tbody>

        </table>
      </div>

    </div>
  </div>
</div>




</body>

