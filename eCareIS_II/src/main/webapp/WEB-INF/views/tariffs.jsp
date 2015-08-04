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

<%@ include file="message.jsp" %>

<div class="container-fluid">
  <div class="row">

    <div class="col-md-5 main" style="position:relative; ">
      <H3> Создать новый тариф:</H3>

      <sf:form role="form" method="post" modelAttribute="newTariff" action="${pageContext.request.contextPath}/app/tariffs/add">
        <div class="form-group">
          <sf:label path="title" for="title">Название тарифа:</sf:label>
          <sf:input path="title" name="title" type="text" class="form-control" id="title" placeholder="Введите название тарифа"/>
          <sf:errors path="title" cssClass="errorlabel" />
        </div>

        <div class="form-group">
          <sf:label path="price" for="price">Абонентская плата:</sf:label>
          <sf:input path="price" name="price" type="text" class="form-control" id="price" placeholder="Абонентская плата"/>
          <sf:errors path="price" cssClass="errorlabel" />
        </div>

        <div class="control-buttons">
          <button type="submit" class="btn btn-primary">Создать новый тариф</button>
        </div>

        <input type="hidden" name="command" value="addTariff"/>
      </sf:form>

    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="row">

    <br><br>
    <div class="col-md-11 main" style="position:relative; ">

      <H3> Список существующих тарифов:</H3>
      <table class="table table-striped">
        <%--<h3> All users</h3>--%>
        <thead>
        <tr>
          <th> Название тарифа </th>
          <th> Стоимость </th>
          <th> Удаление тарифа </th>
          <th> Добавление/удаление опций </th>
        </tr>

        </thead>
        <tbody>
        <c:forEach  var="tariff" items="${tariffs}">
          <tr>

            <td>
                ${tariff.title}
            </td>

            <td>
                ${tariff.price}
            </td>

            <td>
              <form class="formButton" action="${pageContext.request.contextPath}/app/tariffs/remove" method="post">
                <input type="hidden" name="tariffId" value="${tariff.tariffId}"/>
                <input type="hidden" name="command" value="delete"/>
                <input type="submit" class="btn btn-link btn-xs" value="Удалить"/>
              </form>
            </td>

            <td>
              <form class="formButton" action="${pageContext.request.contextPath}/app/tariffs/PossibleOptions" method="post">
                <input type="hidden" name="tariffId" value="${tariff.tariffId}"/>
                <input type="hidden" name="command" value="editTariffOption"/>
                <input type="submit" class="btn btn-link btn-xs" value="Редактировать опции"/>
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
