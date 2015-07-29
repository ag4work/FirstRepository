<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="all">

</head>


<body>


<%@ include file="headermenu.jsp" %>
<%@ include file="message.jsp" %>

<div class="container-fluid">
  <div class="row">

    <div class="col-md-5 main" style="position:relative; ">
      <H3> Создать новую опцию:</H3>

      <sf:form role="form" method="post" modelAttribute="newOption" action="${pageContext.request.contextPath}/app/options/add">
        <%--<sf:errors path="*" cssClass="bg-danger" element="p"/>--%>
        <div class="form-group">
          <sf:label path="title">Название опции:</sf:label>
          <sf:input path="title" type="text" class="form-control"  id="title" placeholder="Введите название опции"/>
          <sf:errors path="title" cssClass="error" />
        </div>

        <div class="form-group">
          <sf:label path="monthlyCost">Абонентская плата:</sf:label>
          <sf:input path="monthlyCost" type="text" class="form-control"  id="monthlyCost" placeholder="Абонентская плата"/>
          <sf:errors path="monthlyCost" cssClass="error" />
        </div>

        <div class="form-group">
          <sf:label path="activationCharge">Стоимость подключения:</sf:label>
          <sf:input path="activationCharge" type="text" class="form-control" id="activationCharge" placeholder="Стоимость подключения"/>
          <sf:errors path="activationCharge" cssClass="error" />
        </div>

        <div class="control-buttons">
          <button type="submit" class="btn btn-primary">Создать новую опцию</button>
        </div>
        <%--<input type="hidden" name="command" value="addOption"/>--%>
      </sf:form>

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
              <a class="btn btn-danger btn-xs"
                 href="${pageContext.request.contextPath}/app/options/remove/${option.optionId}">
                Удалить
              </a>

              <%--<form class="formButton" action="optionDelete.sec" method="post">--%>
                <%--<input type="hidden" name="optionId" value="${option.optionId}"/>--%>
                <%--<input type="hidden" name="command" value="delete"/>--%>
                <%--<input type="submit" class="btn btn-link btn-xs" value="Удалить"/>--%>
              <%--</form>--%>
            </td>

            <td>
              <%--<a class="btn btn-info btn-xs"--%>
                 <%--href="${pageContext.request.contextPath}/app/options/edit/${option.optionId}">--%>
                <%--Завис./несовм.--%>
              <%--</a>--%>

            <form class="formButton" action="${pageContext.request.contextPath}/app/options/edit/" method="post">
                <input type="hidden" name="optionId" value="${option.optionId}"/>
                <%--<input type="hidden" name="command" value="setDepAndInconsistOptions"/>--%>
                <input type="submit" class="btn btn-info btn-xs" value="Завис./несовм."/>
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
