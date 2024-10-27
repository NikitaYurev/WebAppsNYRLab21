<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>TTNs List - Orders Processing</title>
</head>
<body>
<div class="content">
  <h1>TTNs List - Orders Processing</h1>

  <!-- Check if there are any TTNs to display -->
  <c:if test="${ttns == null || ttns.isEmpty()}">
    <p>No TTNs available.</p>
  </c:if>

  <c:if test="${ttns != null && !ttns.isEmpty()}">
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Receiver</th>
        <th>Manager</th>
        <th>Number of Point</th>
        <th>Code TTN</th>
        <th>Send Time</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      <c:forEach var="ttn" items="${ttns}">
        <tr>
          <td><c:out value="${ttn.id}"/></td>
          <td><c:out value="${ttn.receiver.firstName} ${ttn.receiver.secondName}"/></td>
          <td><c:out value="${ttn.manager}"/></td>
          <td><c:out value="${ttn.numPoint}"/></td>
          <td><c:out value="${ttn.kodTTN}"/></td>
          <td><c:out value="${ttn.sendTime}"/></td>
          <td><c:out value="${ttn.status}"/></td>
          <td>
            <!-- Edit Link -->
            <form action="<c:url value='/newpost/update' />" method="get" style="display:inline;">
              <input type="hidden" name="id_ttn" value="${ttn.id}" />
              <input type="submit" value="Edit" />
            </form>

            <!-- Delete Link -->
            <form action="<c:url value='/newpost/delete' />" method="post" style="display:inline;">
              <input type="hidden" name="id_ttn" value="${ttn.id}" />
              <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this TTN?')" />
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:if>

  <!-- Link to create a new TTN -->
  <p>
    <a href="<c:url value='/newpost/create' />">Create New TTN</a>
  </p>
</div>
</body>
</html>
