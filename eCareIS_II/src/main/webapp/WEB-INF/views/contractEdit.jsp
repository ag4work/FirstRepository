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
        <h2> Контракт:</h2>

        <h4 class="darkgrey">${contract.userDTO.name} ${contract.userDTO.lastname} <br></h4>
        <h4> Номер телефона: <span class="darkgrey"> +7 (${fn:substring(contract.phoneNumber,0 ,3)}) ${fn:substring(contract.phoneNumber,3 ,6)}-${fn:substring(contract.phoneNumber,6 ,8)}-${fn:substring(contract.phoneNumber,8 ,10)} </span><br> </h4>
        <h4> Баланс: <span class="darkgrey"> ${contract.balance} руб.</span> <br> </h4>
        <h4> Тариф: <span class="darkgrey"> ${contract.tariffDTO.title}</span> <br> </h4>

        <%--<ul class="list-group">--%>
        <%--<c:forEach  var="contractOption" items="${contract.chosenOption}">--%>
        <%--<li class="list-group-item">${contractOption.title}</li>--%>
        <%--</c:forEach>--%>
        <%--</ul>--%>


      </div>

      <div>


        <c:if test="${not empty contractOptions}">

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

        </c:if>
        <c:if test="${empty contractOptions}">
          <h4> Опции: <span class="darkgrey">не подключены</span>  </h4>
        </c:if>
      </div>


      <br>

      <div>
        <h2> Корзина:</h2>
        <c:if test="${!empty cart}">
          <h4> Тариф: <span class="darkgrey">${cart.tariffDTO.title} </span> </h4>

          <c:if test="${!empty cart.optionDTOset}">
            <h4> Список опций: </h4>
            <ul class="list-group">
              <c:forEach  var="optionInCart" items="${cart.optionDTOset}">
                <li class="list-group-item">${optionInCart.title}, <span class="darkgrey"> ${optionInCart.activationCharge} руб. </span> </li>
              </c:forEach>
            </ul>
          </c:if>

          <h4> Всего к оплате: <span class = "darkgrey">${totalPaymentForCart} руб. </span> </h4>

          <span>
            <form role="form" method="post" action="${pageContext.request.contextPath}/app/PayForCart">
              <div class="control-buttons">
                <button type="submit" class="btn btn-primary">Оплатить</button>
              </div>
              <input type="hidden" name="contractId" value="${contract.contractId}"/>
            </form>

            <form role="form" method="post" action="${pageContext.request.contextPath}/app/resetCart">
              <div class="control-buttons">
                <button type="submit" class="btn btn-primary">Очистить</button>
              </div>
              <input type="hidden" name="contractId" value="${contract.contractId}"/>
              <input type="hidden" name="tariffId" value="${tariffs.get(0).tariffId}"/>

            </form>

          </span>

        </c:if>
        <c:if test="${empty cart}">
          <h4 class="darkgrey">    -- пусто --</h4>
        </c:if>


      </div>

    </div>


    <div class="col-md-8 main" style="position:relative; ">
      <div>
        <h3>Выберите тариф:</h3>

        <form name=frmTest action="${pageContext.request.contextPath}/app/contractEdit" method=POST>
          <%--<label for="sel1">Выберите тариф из списка:</label>--%>
          <select class="form-control" name="tariffId" id="sel1" onChange="frmTest.submit();">
            <c:forEach var="tariff" items="${tariffs}">
              <option value="${tariff.tariffId}"> ${tariff.title}, ${tariff.price} руб. </option>
            </c:forEach>
          </select>
          <input type="hidden" name="contractId" value="${contract.contractId}"/>
        </form>

      </div>
      <br>
      <form class="formButton" action="${pageContext.request.contextPath}/app/addTariffOnlyToCart" method="post">
        <input type="hidden" name="tariffId" value="${tariffs.get(0).tariffId}"/>
        <input type="hidden" name="contractId" value="${contract.contractId}"/>
        <input type="submit" class="btn btn-info btn-xs" value="Добавить тариф в корзину"/>
      </form>

      <br>
      <c:if test="${not empty chosenTariffOptions}">

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
              <th>  </th>
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

      </c:if>
      <c:if test="${empty chosenTariffOptions}">
        <h5> На данном тарифе опций не предусмотрено</h5>

      </c:if>
    </div>
  </div>
</div>




</body>

