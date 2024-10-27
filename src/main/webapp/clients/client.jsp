<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Client</title>
</head>
<body>
<div class="content">
    <h1>Add Client - Orders Processing</h1>
    <c:if test="${error != null}">
        <p style="color: red;"><b>${error}</b></p>
    </c:if>
    <form action="create" method="post">
        <p><label for="firstName">First Name</label></p>
        <input type="text" name="firstName" id="firstName" required>

        <p><label for="secondName">Last Name</label></p>
        <input type="text" name="secondName" id="secondName" required>

        <p><label for="region">Region</label></p>
        <input type="text" name="region" id="region" required>

        <p><label for="city">City</label></p>
        <input type="text" name="city" id="city" required>

        <p><label for="phone">Phone</label></p>
        <input type="text" name="phone" id="phone" pattern="\+\d{10,15}" required>

        <p><label for="email">Email</label></p>
        <input type="email" name="email" id="email" required>

        <p>
            <button type="submit">Save</button>
            <a href="${pageContext.request.contextPath}/clients/list">Cancel</a>
        </p>
    </form>
</div>
</body>
</html>
