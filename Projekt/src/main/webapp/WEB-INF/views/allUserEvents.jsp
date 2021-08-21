<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 08.08.2021
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>Moje eventy</center>
<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
    </tr>
<c:forEach items="${events}" var="events">
    <tr>
        <td>${events.name}</td>
        <td>${events.description}</td>
        <td>
            <a href='<c:url value="/event/details?id=${events.id}"></c:url>'>Szczegóły</a>
        </td>
        <td>
            <a href='<c:url value="/event/edit?id=${events.id}"></c:url> '>Edytuj</a>
        </td>
        <td>
            <a href='<c:url value="/event/delete?id=${events.id}"></c:url> '>Usun</a>
        </td>
    </tr>
</c:forEach>
</table>
<br><br><br>
<center>Eventy do którch dołączyłem</center>
<br><br><br>

<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
    </tr>
    <c:forEach items="${joined}" var="joined">
        <tr>
            <td>${joined.name}</td>
            <td>${joined.description}</td>
            <td>
                <a href='<c:url value="/event/details?id=${joined.id}"></c:url>'>Szczegóły</a>
            </td>
        </tr>
    </c:forEach>
</table>




<br><br><br>
<a href='<c:url value="/user/homePage"></c:url> '>Powrot</a>
</body>
</html>
