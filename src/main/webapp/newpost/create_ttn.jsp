<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Create New TTN</title>
</head>
<body>
<div class="content">
  <h1>Create New TTN - Orders Processing</h1>

  <!-- Display general error if something is wrong -->
  <c:if test="${error != null}">
    <p style="color: red;"><b>${error}</b></p>
  </c:if>

  <!-- Check if the clients list is empty -->
  <c:if test="${clients == null || clients.isEmpty()}">
    <p style="color: red;">No clients available. Please add clients before creating a new TTN.</p>
    <!-- Directly linking to clients list -->
    <a href="../clients/list">Go to Clients List</a>
  </c:if>

  <c:if test="${clients != null && !clients.isEmpty()}">
    <!-- Directly linking to create TTN action -->
    <form action="../newpost/create" method="post" onsubmit="return validateForm()">
      <!-- Receiver Selection -->
      <p>
        <label for="receiver">Receiver (Client)</label>
      </p>
      <p>
        <select name="receiver" id="receiver" required>
          <c:forEach var="client" items="${clients}">
            <option value="${client.id}">
                ${client.firstName} ${client.secondName}
            </option>
          </c:forEach>
        </select>
      </p>

      <!-- Manager -->
      <p>
        <label for="manager">Manager</label>
      </p>
      <p>
        <input type="text" name="manager" id="manager" required/>
      </p>

      <!-- Number of Point -->
      <p>
        <label for="numPoint">Number of Point</label>
      </p>
      <p>
        <input type="number" name="numPoint" id="numPoint" required min="1"/>
      </p>

      <!-- TTN Code -->
      <p>
        <label for="kodTTN">Code TTN</label>
      </p>
      <p>
        <input type="text" name="kodTTN" id="kodTTN" maxlength="20" required/>
      </p>

      <!-- Send Time -->
      <p>
        <label for="sendTime">Send Time</label>
      </p>
      <p>
        <input type="datetime-local" name="sendTime" id="sendTime" required/>
      </p>

      <!-- Status -->
      <p>
        <label for="status">Status</label>
      </p>
      <p>
        <select name="status" id="status" required>
          <c:forEach var="status" items="${statuses}">
            <option value="${status}">${status}</option>
          </c:forEach>
        </select>
      </p>

      <!-- Save and Cancel Buttons -->
      <p>
        <button type="submit">Create TTN</button>
        <!-- Directly linking to TTNs list -->
        <a href="../newpost">Cancel</a>
      </p>
    </form>
  </c:if>
</div>

<!-- JavaScript validation to prevent accidental empty fields -->
<script>
  function validateForm() {
    let manager = document.getElementById("manager").value.trim();
    let numPoint = document.getElementById("numPoint").value.trim();
    let kodTTN = document.getElementById("kodTTN").value.trim();
    let sendTime = document.getElementById("sendTime").value.trim();

    if (!manager || !numPoint || !kodTTN || !sendTime) {
      alert("Please fill in all required fields.");
      return false;
    }
    return true;
  }
</script>
</body>
</html>
