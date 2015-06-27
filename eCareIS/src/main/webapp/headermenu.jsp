<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 27.06.2015
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8"  pageEncoding="utf8"%>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">eCare</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <!--<li class="active"><a href="#">Users</a></li>-->
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/users">All users</a></li>
            <li><a href="/addUser">Register new user</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Contracts <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">All contracts</a></li>
            <li><a href="#">Register new user</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Tariffs <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">All tariffs</a></li>
            <li><a href="#">New tariff</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Options <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">All options</a></li>
            <li><a href="#">New option</a></li>
          </ul>
        </li>

      </ul>
    </div>
  </div>
</nav>