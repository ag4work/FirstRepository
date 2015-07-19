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
<%@ include file="message.jsp" %>
<div class="container-fluid">
  <div class="row">

    <div class="col-md-5 main" style="position:relative; ">
      <H3> Создать новую опцию:</H3>

      <form role="form" method="post" action="addOption.sec">
        <div class="form-group">
          <label for="title">Название опции:</label>
          <input name="title" type="text" class="form-control" id="title" placeholder="Введите название опции">
        </div>

        <div class="form-group">
          <label for="monthlyCost">Абонентская плата:</label>
          <input name="monthlyCost" type="text" class="form-control" id="monthlyCost" placeholder="Абонентская плата">
        </div>

        <div class="form-group">
          <label for="activationCharge">Стоимость подключения:</label>
          <input name="activationCharge" type="text" class="form-control" id="activationCharge" placeholder="Стоимость подключения">
        </div>

        <div class="control-buttons">
          <button type="submit" class="btn btn-primary">Создать новую опцию</button>
        </div>

        <input type="hidden" name="command" value="addOption"/>
      </form>

    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="row">

    <br><br>
    <div class="col-md-11 main" style="position:relative; ">
      <H3> Список существующих опций:</H3>

      <table class="table table-striped">
        <%--<h3> All users</h3>--%>
        <thead>
        <tr>
          <th> Название опции </th>
          <th> Ежемесячная плата </th>
          <th> Стоимость подключения </th>
          <th> Требуемые опции</th>
          <th> Зависимые опции</th>
          <th> Несовместимые опции</th>
          <th> Удаление опции </th>
          <th> Выбрать зависимые и несовместимые опции </th>
        </tr>

        </thead>
        <tbody>
        <c:forEach  var="option" items="${options}">
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
              <c:forEach  var="reqQption" items="${option.requiredOption}">
                ${reqQption.title} <br>
              </c:forEach>
            </td>

            <td>
              <c:forEach  var="depQption" items="${option.dependentOption}">
                ${depQption.title} <br>
              </c:forEach>
            </td>

            <td>
              <c:forEach  var="inconsistentOption" items="${option.inconsistentOption}">
                ${inconsistentOption.title} <br>
              </c:forEach>
            </td>


            <td>
              <form class="formButton" action="optionDelete.sec" method="post">
                <input type="hidden" name="optionId" value="${option.optionId}"/>
                <input type="hidden" name="command" value="delete"/>
                <input type="submit" class="btn btn-link btn-xs" value="Удалить"/>
              </form>
            </td>

            <td>
              <form class="formButton" action="OptionsSetDepAndInconsistLists.sec" method="post">
                <input type="hidden" name="optionId" value="${option.optionId}"/>
                <input type="hidden" name="command" value="setDepAndInconsistOptions"/>
                <input type="submit" class="btn btn-link btn-xs" value="Завис./несовм."/>
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
