<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 13.08.2021
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Imie</th>
        <th>Nazwisko</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${users}" var="users">

        <tr>
            <td>${users.name}</td>
            <td>${users.lastName}</td>
            <td>${users.username}</td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
