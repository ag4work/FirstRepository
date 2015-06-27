<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 27.06.2015
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
  <c:if test="${!empty successText}">
    <p class="alert alert-success" style="padding: 10px;">
        ${successText}
    </p>
  </c:if>

  <c:if test="${!empty infoText}">
    <p class="alert alert-info" style="padding: 20px;">
        ${infoText}
    </p>
  </c:if>

  <c:if test="${!empty errorText}">
    <p class="alert alert-danger" style="padding: 20px;">
        ${errorText}
    </p>
  </c:if>
</div>
