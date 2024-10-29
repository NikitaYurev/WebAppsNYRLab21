<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>Orders Processing - Home</title>
</head>
<body>
<div style="text-align: center;">
  <h1>Welcome to Orders Processing System</h1>
  <br/>
  <h3><a href="clients/list">Show All Clients</a></h3>
  <br/>
  <jsp:useBean id="now" class="java.util.Date" />
  <h3><fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long" /></h3>
</div>
</body>
</html>
