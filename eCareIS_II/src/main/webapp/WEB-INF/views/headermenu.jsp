<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 27.06.2015
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8"  pageEncoding="utf8"%>

<div align="right">
  <form  method="get" action="${pageContext.request.contextPath}/logout">
    <button type="submit" class="btn btn-default">logout</button>
  </form>

</div>
<%--sessionUserInfo--%>
<c:if test="${sessionUserInfo.role==0}">


  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">eCare</a>
      </div>
      <div>
        <ul class="nav navbar-nav">
          <!--<li class="active"><a href="#">Users</a></li>-->
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="users.sec">All users</a></li>
              <li><a href="addUser.sec">Register new user</a></li>
            </ul>
          </li>
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Contracts <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="contracts.sec">All contracts</a></li>
            </ul>
          </li>
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Tariffs <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="allTariffs.sec">All tariffs</a></li>
            </ul>
          </li>
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Options <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="allOptions.sec">All options</a></li>
            </ul>
          </li>

        </ul>
      </div>
    </div>
  </nav>
</c:if>

<c:if test="${sessionUserInfo.role==1}">


  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">eCare</a>
      </div>
      <div>
        <ul class="nav navbar-nav">
          <li class="active"><a href="clientContractDashboard.sec">Кабинет</a></li>
        </ul>
      </div>
    </div>
  </nav>
</c:if>
