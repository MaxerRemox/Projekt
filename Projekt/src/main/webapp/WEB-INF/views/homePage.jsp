<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 28.07.2021
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href='<c:url value="/user/add"/>'>Rejestracja</a><br>
<form:form modelAttribute="username">
    <c:choose>
        <c:when test="${username == null}">
            <a href='<c:url value="/user/login"/>'>Logowanie</a>
        </c:when>
        <c:otherwise>
            <a href='<c:url value="/user/login"/>'>Panel uzytkownika</a>
        </c:otherwise>
    </c:choose>
</form:form>
</body>
</html>
