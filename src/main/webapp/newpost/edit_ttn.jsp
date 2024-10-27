<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit TTN</title>
</head>
<body>
<div class="content">
  <h1>Edit TTN - Orders Processing</h1>

  <!-- Display general error if something is wrong -->
  <c:if test="${error != null}">
    <p style="color: red;"><b>${error}</b></p>
  </c:if>

  <!-- Check if the TTN or clients are missing and display a friendly message -->
  <c:if test="${ttn == null}">
    <p style="color: red;">Error: TTN not found. Please try again or contact support.</p>
    <a href="../newpost">Back to TTN List</a>
  </c:if>

  <c:if test="${clients == null || clients.isEmpty()}">
    <p style="color: red;">No clients available. Please add clients before editing TTNs.</p>
    <a href="../clients/list">Go to Clients List</a>
  </c:if>

  <c:if test="${ttn != null && clients != null && !clients.isEmpty()}">
    <form action="../newpost/update" method="post" onsubmit="return validateForm()">
      <!-- Hidden field for TTN ID -->
      <input type="hidden" name="id_ttn" value="${ttn.id}"/>

      <!-- Receiver Selection -->
      <p>
        <label for="receiver">Receiver (Client)</label>
      </p>
      <p>
        <select name="receiver" id="receiver" required>
          <c:forEach var="client" items="${clients}">
            <option value="${client.id}" ${ttn.receiver.id == client.id ? 'selected' : ''}>
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
        <input type="text" name="manager" id="manager" value="${ttn.manager}" required/>
      </p>

      <!-- Number of Point -->
      <p>
        <label for="numPoint">Number of Point</label>
      </p>
      <p>
        <input type="number" name="numPoint" id="numPoint" value="${ttn.numPoint}" required min="1"/>
      </p>

      <!-- TTN Code -->
      <p>
        <label for="kodTTN">Code TTN</label>
      </p>
      <p>
        <input type="text" name="kodTTN" id="kodTTN" value="${ttn.kodTTN}" maxlength="20" required/>
      </p>

      <!-- Send Time -->
      <p>
        <label for="sendTime">Send Time</label>
      </p>
      <p>
        <input type="datetime-local" name="sendTime" id="sendTime"
               value="${formattedSendTime}" required />
      </p>

      <!-- Status -->
      <p>
        <label for="status">Status</label>
      </p>
      <p>
        <select name="status" id="status" required>
          <c:forEach var="status" items="${statuses}">
            <option value="${status}" ${ttn.status == status ? 'selected' : ''}>${status}</option>
          </c:forEach>
        </select>
      </p>

      <!-- Save and Cancel Buttons -->
      <p>
        <button type="submit">Save Changes</button>
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
