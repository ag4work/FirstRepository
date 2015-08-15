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

<%--<%@ include file="headermenu.jsp" %>--%>
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
        <br>

        <div>
          <h2> Контракт:</h2>
          <h4><span class="darkgrey"> ${contract.userDTO.name} ${contract.userDTO.lastname} </span> <br></h4>
          <h4> Номер телефона: <span class="darkgrey"> +7 (${fn:substring(contract.phoneNumber,0 ,3)}) ${fn:substring(contract.phoneNumber,3 ,6)}-${fn:substring(contract.phoneNumber,6 ,8)}-${fn:substring(contract.phoneNumber,8 ,10)} </span><br> </h4>
          <h4> Баланс: <span class="darkgrey">${contract.balance} руб.</span> <br> </h4>

            <form class="formButton" action="contractEdit" method="post">
              <input type="hidden" name="contractId" value="${contract.contractId}"/>
              <input type="hidden" name="tariffId" value="-1"/>
              <input type="submit" class="btn btn-edit btn-xs" value="Тарифы и опции"/>
            </form>
          <c:if test="${contract.blocked == true}">
            <c:if test="${contract.blockedByStaff== false}">
            <h4> Статус: <span class="darkgrey"> Заблокирован клиентом </span><br> </h4>

              <form class="formButton" action="clientContractDashboardBlockUnblock" method="post">
                <input type="hidden" name="contractId" value="${contract.contractId}"/>
                <input type="hidden" name="command" value="unblock"/>
                <input type="submit" class="btn btn-success btn-xs" value="Разблокировать"/>
              </form>
            </c:if>
            <c:if test="${contract.blockedByStaff== true}">
            <h4> Статус: <span class="darkgrey"> заблокирован сотрудником оператора </span><br> </h4>
            </c:if>

          </c:if>

          <c:if test="${contract.blocked == false}">
            <h4> Статус: <span class="darkgrey"> Online </span><br> </h4>
            <form class="formButton" action="clientContractDashboardBlockUnblock" method="post">
              <input type="hidden" name="contractId" value="${contract.contractId}"/>
              <input type="hidden" name="command" value="block"/>
              <input type="submit" class="btn btn-edit btn-xs" value="Блокировать"/>
            </form>
          </c:if>

        </div>


        <%--<h4>${contract.userDTO.name} ${contract.userDTO.lastname} <br></h4>--%>
        <%--<h4> Номер телефона: ${contract.phoneNumber} <br> </h4>--%>
        <%--<h4> Баланс: ${contract.balance} руб. <br> </h4>--%>
        <%--<h4> Тариф: "${contract.tariffDTO.title}" <br> </h4>--%>

        <%--<form class="formButton" action="contractEdit.sec" method="post">--%>
        <%--<input type="hidden" name="contractId" value="${contract.contractId}"/>--%>
        <%--<input type="submit" class="btn btn-link btn-xs" value="Редактировать контракт"/>--%>
        <%--</form>--%>

      </div>
    </div>



  </div>
</div>




</body>

