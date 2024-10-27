<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style type="text/css">
    .content {
        max-width: 800px;
        margin: auto;
    }
    label {
        font-style: italic;
        font-family: "Arial Black";
    }
</style>
<head>
    <meta charset="UTF-8">
    <title>${titleClient} - Orders Processing</title>
</head>
<body>
<div class="content">
    <caption>
        <br>
        <h1><c:out value="${titleClient}"></c:out> - Orders Processing</h1>
    </caption>
    <br>
    <c:if test="${fn:length(errorString)>0}">
        <p style="color: red"><b><c:out value="${errorString}"/></b></p>
        <br>
    </c:if>
    <form action="client" method="post">
        <input type="hidden" name="id_client" value="${client.id}" required>
        <p>
            <label for="firstName">First Name</label>
        </p>
        <p>
            <input type="text" name="firstName" id="firstName" value="${client.firstName}" required/>
        </p>
        <p>
            <label for="secondName">Last Name</label>
        </p>
        <p>
            <input type="text" name="secondName" id="secondName" value="${client.secondName}" required/>
        </p>
        <p>
            <label for="region">Region</label>
        </p>
        <p>
            <input type="text" name="region" id="region" value="${client.region}" required/>
        </p>
        <p>
            <label for="city">City</label>
        </p>
        <p>
            <select name="city" id="city" required>
                <c:forEach var="city" items="${cityList}">
                    <option value="${city}" ${client.city == city ? 'selected' : ''}>${city}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label for="phone">Phone</label>
        </p>
        <p>
            <input type="text" name="phone" id="phone" value="${client.phone}"
                   pattern="\\+\\d{12}" required/>
        </p>
        <p>
            <label for="email">Email</label>
        </p>
        <p>
            <input type="email" name="email" id="email" value="${client.email}" required/>
        </p>
        <p>
            <button type="submit">SAVE</button>
            <a href="<c:out value="${pageContext.request.contextPath}/clients"/>">Cancel</a>
        </p>
    </form>
</div>
</body>
</html>
