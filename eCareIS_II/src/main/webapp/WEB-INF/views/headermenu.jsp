<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 27.06.2015
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8"  pageEncoding="utf8"%>

<%--<div align="right">--%>
  <%--<form  method="get" action="${pageContext.request.contextPath}/logout">--%>
    <%--<button type="submit" class="btn btn-default">logout</button>--%>
  <%--</form>--%>

<%--</div>--%>
<%--sessionUserInfo--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mystyles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style1.css">


<c:if test="${sessionUserInfo.role==0}">


  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">eCare</a>
      </div>
      <div>
        <ul class="nav navbar-nav">
          <!--<li class="active"><a href="#">Users</a></li>-->
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
              <span class="glyphicon glyphicon-user circle orange"></span>
              USERS <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href="${pageContext.request.contextPath}/app/users">All users</a></li>
              <li><a href="${pageContext.request.contextPath}/app/users/add">Register new user</a></li>
            </ul>
          </li>
          <li><a href="${pageContext.request.contextPath}/app/contracts">
            <span class="glyphicon glyphicon-phone green circle"></span>  CONTRACTS
          </a>
          </li>
          <li><a href="${pageContext.request.contextPath}/app/tariffs">
            <span class="glyphicon glyphicon-duplicate blue circle"></span>
            TARIFFS
          </a>
          </li>
          <li><a href="${pageContext.request.contextPath}/app/options">
            <span class="glyphicon glyphicon-list circle blue"></span>
            OPTIONS
          </a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionUserInfo.name} </a></li>
          <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
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
          <li><a href="${pageContext.request.contextPath}/app/clientDashboard">Кабинет</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionUserInfo.name} </a></li>
          <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
        </ul>
      </div>
    </div>
  </nav>
</c:if>
