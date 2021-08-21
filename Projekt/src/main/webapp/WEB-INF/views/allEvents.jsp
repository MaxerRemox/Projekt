<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 10.08.2021
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/event/join" method="post">
<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
    </tr>
    <c:forEach items="${list}" var="events">
        <tr>
            <td>${events.name}</td>
            <td>${events.description}</td>
            <td>
                <a href='<c:url value="/event/join?id=${events.id}"></c:url>'>Dołącz</a>
            </td>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>
