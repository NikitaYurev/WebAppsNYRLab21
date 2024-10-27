<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${titleNewPostTTN} - Orders Processing</title>
</head>
<body>
<div class="content">
  <h1><c:out value="${titleNewPostTTN}"/> - Orders Processing</h1>

  <c:if test="${fn:length(errorString)>0}">
    <p style="color: red"><b><c:out value="${errorString}"/></b></p>
  </c:if>

  <form action="manage" method="post">
    <input type="hidden" name="id" value="${ttn.id}"/>

    <p>
      <label for="receiver">Receiver (Client)</label>
    </p>
    <p>
      <select name="receiver" id="receiver" required>
        <c:forEach var="client" items="${clientList}">
          <option value="${client.id}" ${ttn.receiver.id == client.id ? 'selected' : ''}>
              ${client.firstName} ${client.secondName}
          </option>
        </c:forEach>
      </select>
    </p>

    <p>
      <label for="manager">Manager</label>
    </p>
    <p>
      <input type="text" name="manager" id="manager" value="${ttn.manager}" required/>
    </p>

    <p>
      <label for="numPoint">Number of Point</label>
    </p>
    <p>
      <input type="number" name="numPoint" id="numPoint" value="${ttn.numPoint}" required/>
    </p>

    <p>
      <label for="kodTTN">Code TTN</label>
    </p>
    <p>
      <input type="text" name="kodTTN" id="kodTTN" value="${ttn.kodTTN}" maxlength="20" required/>
    </p>

    <p>
      <label for="sendTime">Send Time</label>
    </p>
    <p>
      <input type="datetime-local" name="sendTime" id="sendTime" value="${ttn.sendTime}" required/>
    </p>

    <p>
      <label for="status">Status</label>
    </p>
    <p>
      <select name="status" id="status" required>
        <c:forEach var="status" items="${statusList}">
          <option value="${status}" ${ttn.status == status ? 'selected' : ''}>${status}</option>
        </c:forEach>
      </select>
    </p>

    <p>
      <button type="submit">SAVE</button>
      <a href="<c:out value="${pageContext.request.contextPath}/newpost"/>">Cancel</a>
    </p>
  </form>
</div>
</body>
</html>
