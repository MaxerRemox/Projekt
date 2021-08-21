<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 13.08.2021
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="event" method="post" action="/event/editSave">
    <form:hidden path="id" value="${event.id}"></form:hidden>
    Nazwa: <form:input path="name"></form:input><br>
    Opis: <form:textarea path="description"></form:textarea><br>
    Miejsce: <form:input path="place"></form:input><br>
    Data: <form:input type="date" path="date"></form:input><br>
    <input type="submit" value="Zapisz zmiany">
</form:form>
</body>
</html>
