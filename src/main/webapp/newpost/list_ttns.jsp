<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>TTNs List - Orders Processing</title>
  <style>
    .filter-form {
      display: flex;
      flex-wrap: wrap;
      gap: 10px;
      align-items: center;
    }
    .filter-form label {
      margin-right: 5px;
    }
    .filter-form input, .filter-form select {
      margin-right: 15px;
    }
    .filter-form button {
      margin-top: 10px;
    }
  </style>
</head>
<body>
<div class="content">
  <h1>TTNs List - Orders Processing</h1>

  <!-- Link to create a new TTN -->
  <p>
    <a href="<c:url value='/newpost/create' />">Create New TTN</a>
  </p>

  <!-- Filter form -->
  <form action="${pageContext.request.contextPath}/newpost" method="get" class="filter-form">
    <table>
      <tr>
        <td><label for="receiverFilter">Receiver Name:</label></td>
        <td><input type="text" id="receiverFilter" name="receiverFilter" value="${param.receiverFilter}"/></td>
        <td><label for="managerFilter">Manager:</label></td>
        <td><input type="text" id="managerFilter" name="managerFilter" value="${param.managerFilter}"/></td>
      </tr>
      <tr>
        <td><label for="numPointFilter">Number of Point:</label></td>
        <td><input type="text" id="numPointFilter" name="numPointFilter" value="${param.numPointFilter}"/></td>
        <td><label for="kodTTNFilter">Code TTN:</label></td>
        <td><input type="text" id="kodTTNFilter" name="kodTTNFilter" value="${param.kodTTNFilter}"/></td>
      </tr>
      <tr>
        <td><label for="statusFilter">Status:</label></td>
        <td colspan="3">
          <select id="statusFilter" name="statusFilter">
            <option value="">All</option>
            <option value="SENT" <c:if test="${param.statusFilter == 'SENT'}">selected</c:if>>Sent</option>
            <option value="RECEIVED" <c:if test="${param.statusFilter == 'RECEIVED'}">selected</c:if>>Received</option>
            <option value="RETURNED" <c:if test="${param.statusFilter == 'RETURNED'}">selected</c:if>>Returned</option>
          </select>
        </td>
      </tr>
      <tr>
        <td colspan="4" style="text-align: right;"><button type="submit">Filter</button></td>
      </tr>
    </table>
    <button type="submit">Filter</button>
  </form>
  <br>

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


</div>
</body>
</html>
