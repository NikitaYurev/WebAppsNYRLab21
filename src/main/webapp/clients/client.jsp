<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${titleClient} - Orders Processing</title>
</head>
<body>
<div class="content">
    <h1><c:out value="${titleClient}"/> - Orders Processing</h1>

    <c:if test="${fn:length(errorString)>0}">
        <p style="color: red"><b><c:out value="${errorString}"/></b></p>
    </c:if>

    <form action="manage" method="post">
        <!-- <input type="hidden" name="id_client" value="${client.id}"/> -->

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
            <input type="text" name="city" id="city" value="${client.city}" required/>
        </p>

        <p>
            <label for="phone">Phone</label>
        </p>
        <p>
            <input type="text" name="phone" id="phone" value="${client.phone}" pattern="\+\d{8,15}" required/>
        </p>

        <p>
            <label for="email">Email</label>
        </p>
        <p>
            <input type="email" name="email" id="email" value="${client.email}" required/>
        </p>

        <p>
            <button type="submit">SAVE</button>
            <a href="<c:out value="${pageContext.request.contextPath}/clients/list"/>">Cancel</a>
        </p>
    </form>
</div>
</body>
</html>
