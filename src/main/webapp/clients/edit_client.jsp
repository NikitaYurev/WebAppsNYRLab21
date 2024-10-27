<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Client</title>
</head>
<body>
<div class="content">
  <h1>Edit Client - Orders Processing</h1>
  <c:if test="${error != null}">
    <p style="color: red;"><b>${error}</b></p>
  </c:if>
  <form action="${pageContext.request.contextPath}/clients/update" method="post">
    <input type="hidden" name="id_client" value="${client.id}">

    <p><label for="firstName">First Name</label></p>
    <input type="text" name="firstName" id="firstName" value="${client.firstName}" required>

    <p><label for="secondName">Last Name</label></p>
    <input type="text" name="secondName" id="secondName" value="${client.secondName}" required>

    <p><label for="region">Region</label></p>
    <input type="text" name="region" id="region" value="${client.region}" required>

    <p><label for="city">City</label></p>
    <input type="text" name="city" id="city" value="${client.city}" required>

    <p><label for="phone">Phone</label></p>
    <input type="text" name="phone" id="phone" value="${client.phone}" pattern="^\+?\d{10,15}$" required>

    <p><label for="email">Email</label></p>
    <input type="email" name="email" id="email" value="${client.email}" required>

    <p>
      <button type="submit">Save Changes</button>
      <a href="${pageContext.request.contextPath}/clients/list">Cancel</a>
    </p>
  </form>
</div>
</body>
</html>
