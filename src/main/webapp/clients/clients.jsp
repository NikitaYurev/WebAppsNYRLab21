<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders Processing - Clients</title>
</head>
<body>
<h1>Clients List</h1>
<a href="manage">Add New Client</a>
<br><br>
<table border="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Region</th>
        <th>City</th>
        <th>Phone</th>
        <th>Email</th>
        <th colspan="2">Actions</th>
    </tr>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td><c:out value="${client.firstName}"/></td>
            <td><c:out value="${client.secondName}"/></td>
            <td><c:out value="${client.region}"/></td>
            <td><c:out value="${client.city}"/></td>
            <td><c:out value="${client.phone}"/></td>
            <td><c:out value="${client.email}"/></td>
            <td>
                <form action="manage" method="get">
                    <input type="hidden" name="id_client" value="${client.id}"/>
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <td>
                <form action="remove" method="get">
                    <input type="hidden" name="id_client" value="${client.id}"/>
                    <input type="submit" value="Delete" onclick="return confirm('Are you sure?')"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
