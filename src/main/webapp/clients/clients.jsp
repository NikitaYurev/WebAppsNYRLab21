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
<a href="create">Add New Client</a> <!-- Updated link for adding a new client -->
<br><br>

<!-- Filter form -->
<form action="${pageContext.request.contextPath}/clients/list" method="get">
    <table>
        <tr>
            <td><label for="firstNameFilter">First Name:</label></td>
            <td><input type="text" id="firstNameFilter" name="firstNameFilter" value="${param.firstNameFilter}"/></td>
            <td><label for="lastNameFilter">Last Name:</label></td>
            <td><input type="text" id="lastNameFilter" name="secondNameFilter" value="${param.secondNameFilter}"/></td>
        </tr>
        <tr>
            <td><label for="regionFilter">Region:</label></td>
            <td><input type="text" id="regionFilter" name="regionFilter" value="${param.regionFilter}"/></td>
            <td><label for="cityFilter">City:</label></td>
            <td><input type="text" id="cityFilter" name="cityFilter" value="${param.cityFilter}"/></td>
        </tr>
        <tr>
            <td><label for="phoneFilter">Phone:</label></td>
            <td><input type="text" id="phoneFilter" name="phoneFilter" value="${param.phoneFilter}"/></td>
            <td><label for="emailFilter">Email:</label></td>
            <td><input type="text" id="emailFilter" name="emailFilter" value="${param.emailFilter}"/></td>
        </tr>
        <tr>
            <td colspan="4" style="text-align: right;"><button type="submit">Filter</button></td>
        </tr>
    </table>
</form>
<br>

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
            <!-- Updated Edit action -->
            <td>
                <form action="${pageContext.request.contextPath}/clients/update" method="get">
                    <input type="hidden" name="id_client" value="${client.id}" />
                    <button type="submit">Edit</button>
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
