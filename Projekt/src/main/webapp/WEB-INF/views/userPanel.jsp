<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetailsService" %>
<%@ page import="org.springframework.security.core.userdetails.UsernameNotFoundException" %>
<%@ page import="pl.Dominik.app.UserEntityDetails" %>
<%@ page import="pl.Dominik.entity.User" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 29.07.2021
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

To strona po zalogowaniu<br>
Witaj<br>



<a href='<c:url value="/event/add"></c:url>'>Dodaj wydarzenie</a>
<br>
<a href='<c:url value="/event/all"></c:url>'>Lista moich wydarzeń</a><br>
<a href='<c:url value="/event/allEvents"></c:url> '>Wszystkie wydarzenia</a><br>
<a href='<c:url value="/user/homePage"/>'>Powrot</a><br>
<a href="/logout">Wyloguj</a>
</body>
</html>
