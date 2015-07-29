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
<%--<H3> Создать новую опцию:</H3>--%>

<%--<form role="form" method="post" action="addOption.sec">--%>
<%--<div class="form-group">--%>
<%--<label for="title">Название опции:</label>--%>
<%--<input name="title" type="text" class="form-control" id="title" placeholder="Введите название опции">--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label for="monthlyCost">Абонентская плата:</label>--%>
<%--<input name="monthlyCost" type="text" class="form-control" id="monthlyCost" placeholder="Абонентская плата">--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label for="activationCharge">Стоимость подключения:</label>--%>
<%--<input name="activationCharge" type="text" class="form-control" id="activationCharge" placeholder="Стоимость подключения">--%>
<%--</div>--%>

<%--<div class="control-buttons">--%>
<%--<button type="submit" class="btn btn-primary">Создать новую опцию</button>--%>
<%--</div>--%>

<%--<input type="hidden" name="command" value="addOption"/>--%>
<%--</form>--%>

<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<div class="container-fluid">
  <div class="row">

    <div class="col-md-12 main" style="position:relative; ">
      <%@ include file="message.jsp" %>
      <H2> Изменение зависимых и несовместимых опций для опции "${currentOption.title}" </H2>
      <div class="col-md-6 main" style="position:relative; ">
        <H3> Список, которые не являются ни зависимымы, ни несовместимыми для "${currentOption.title}":</H3>
        <table class="table table-striped">
          <%--<h3> All users</h3>--%>
          <thead>
          <tr>
            <th> Название </th>
            <th> Абон. плата </th>
            <th> Стоимость <br> подключения </th>
            <th> Действие </th>
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
                <form class="formButton" action="${pageContext.request.contextPath}/app/options/edit/optionAddDependency" method="post">
                  <input type="hidden" name="optionId" value="${currentOption.optionId}"/>
                  <input type="hidden" name="dependentOptionId" value="${option.optionId}"/>
                  <input type="submit" class="btn btn-link btn-xs" value="К зависимым"/>
                </form>
                <form class="formButton" action="${pageContext.request.contextPath}/app/options/edit/optionAddInconsistency" method="post">
                  <input type="hidden" name="optionId" value="${currentOption.optionId}"/>
                  <input type="hidden" name="inconsistentOptionId" value="${option.optionId}"/>
                  <input type="submit" class="btn btn-link btn-xs" value="К несовместимым"/>
                </form>
              </td>

            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="col-md-6 main" style="position:relative; ">
        <div>
          <H3> Опции, для  которых требуется "${currentOption.title}" (зависимые от нее):</H3>
          <table class="table table-striped">
            <%--<h3> All users</h3>--%>
            <thead>
            <tr>
              <th> Название </th>
              <th> Абон. плата </th>
              <th> Стоимость <br> подключения </th>
              <th> Действие </th>
            </tr>

            </thead>
            <tbody>
            <c:forEach  var="option" items="${currentOption.dependentOption}">
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
                  <form class="formButton" action="${pageContext.request.contextPath}/app/options/edit/deleteOptionDependency" method="post">
                    <input type="hidden" name="optionId" value="${currentOption.optionId}"/>
                    <input type="hidden" name="dependentOptionId" value="${option.optionId}"/>
                    <input type="submit" class="btn btn-link btn-xs" value="Убрать"/>
                  </form>
                </td>

              </tr>
            </c:forEach>
            </tbody>
          </table>

        </div>
        <div>
          <H3> Опции, несовместимые с "${currentOption.title}":</H3>
          <table class="table table-striped">
            <%--<h3> All users</h3>--%>
            <thead>
            <tr>
              <th> Название </th>
              <th> Абон. плата </th>
              <th> Стоимость <br> подключения </th>
              <th> Действие </th>
            </tr>

            </thead>
            <tbody>
            <c:forEach  var="option" items="${currentOption.inconsistentOption}">
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
                  <form class="formButton" action="${pageContext.request.contextPath}/app/options/edit/removeInconsistentOption" method="post">
                    <input type="hidden" name="optionId" value="${currentOption.optionId}"/>
                    <input type="hidden" name="inconsistentOptionId" value="${option.optionId}"/>
                    <input type="submit" class="btn btn-link btn-xs" value="Убрать"/>
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
</div>

</body>
</html>
