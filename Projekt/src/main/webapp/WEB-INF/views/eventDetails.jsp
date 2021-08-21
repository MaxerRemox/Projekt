<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 11.08.2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/event/details" modelAttribute="event">
    <table>
        <tr>
            <th>Nazwa</th>
            <th>Opis</th>
            <th>Data</th>
            <th>Miejsce</th>
            <th>Uczestnicy</th>
            <th>Organizator</th>
        </tr>
        <tr>
            <td>${event.name}</td>
            <td>${event.description}</td>
            <td>${event.date}</td>
            <td>${event.place}</td>
            <td><select name="parti">
            <c:forEach items="${event.participants}" var="parti">
                <option value="${parti.id}">${parti.name} ${parti.lastName}</option>
            </c:forEach>
            </select></td>
            <td>${event.owner.name} ${event.owner.lastName}</td>
        </tr>
    </table>
</form:form>
</body>
</html>
