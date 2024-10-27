<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders Processing - Clients</title>
</head>
<body>
<H1>Clients</H1>
<a href=".">To START</a>
<br>
<table style="border: 1px solid black; border-collapse: collapse" id="clients-table">
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
            <td><c:out value="${client.firstName}"></c:out></td>
            <td><c:out value="${client.secondName}"></c:out></td>
            <td><c:out value="${client.region}"></c:out></td>
            <td><c:out value="${client.city}"></c:out></td>
            <td><c:out value="${client.phone}"></c:out></td>
            <td><c:out value="${client.email}"></c:out></td>
            <td>
                <form action="clients/client" method="GET">
                    <input type="hidden" name="id_client" value="${client.id}"/>
                    <input type="submit" value="UPDATE"/>
                </form>
            </td>
            <td>
                <form action="clients/delete" method="GET">
                    <input type="hidden" name="id_client" value="${client.id}"/>
                    <input type="submit" value="DELETE" onclick="return confirmation()"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript">
    function confirmation() {
        return confirm('Are you sure you want to delete this client?');
    }
</script>
</body>
</html>
