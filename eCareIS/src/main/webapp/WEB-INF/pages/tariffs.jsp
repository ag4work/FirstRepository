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

    <br><br>
    <div class="col-md-11 main" style="position:relative; ">
      <table class="table table-striped">
        <%--<h3> All users</h3>--%>
        <thead>
        <tr>
          <th> Название тарифа </td>
          <th> Стоимость </td>
          <th> Удаление тарифа </td>
          <th> Добавление/удаление опций </td>
        </tr>

        </thead>
        <tbody>
        <c:forEach  var="tariff" items="${tariffList}">
          <tr>

            <td>
                ${tariff.title}
            </td>

            <td>
                ${tariff.price}
            </td>

            <td>
              <form class="formButton" action="Tariffs.sec" method="post">
                <input type="hidden" name="id" value="${tariff.tariffId}"/>
                <input type="hidden" name="command" value="delete"/>
                <input type="submit" class="btn btn-link btn-xs" value="Удалить"/>
              </form>
            </td>

            <td>
              <form class="formButton" action="Tariffs.sec" method="post">
                <input type="hidden" name="id" value="${tariff.tariffId}"/>
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
