<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/xml" %>
<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 28.07.2021
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form modelAttribute="user" method="post" action="/user/save">
    Imie<form:input path="name"></form:input><br>
    Nazwisko<form:input path="lastName"></form:input><br>
    Email<form:input path="username"></form:input><br>
    Haslo<form:password path="password"></form:password><br>
    <input type="submit" value="Zarejestruj">
</form:form>
</body>
</html>
