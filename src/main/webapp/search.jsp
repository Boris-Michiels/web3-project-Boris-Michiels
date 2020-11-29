<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Search"/>
    </jsp:include>

    <main>
        <c:if test="${not empty testResultContactMessage or not empty testResultContacts}">
            <h3>Contacts since test</h3>
            <br>
            <c:choose>
                <c:when test="${not empty testResultContactMessage}">
                    <p>${testResultContactMessage}</p>
                </c:when>
                <c:otherwise>
                    <p>Test date: <c:out value="${testResult.dateString}"/></p>
                    <table id="testResultContacts">
                        <tr>
                            <th>Name</th>
                            <th>GSM</th>
                            <th>Email</th>
                        </tr>
                        <c:forEach var="contact" items="${testResultContacts}">
                            <tr>
                                <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                                <td><c:out value="${contact.phoneNumber}"/></td>
                                <td><c:out value="${contact.email}"/></td>
                            </tr>
                        </c:forEach>
                        <caption>Contacts Overview</caption>
                    </table>
                </c:otherwise>
            </c:choose>
            <br>
        </c:if>

        <h3>Contacts since your latest positive test</h3>
        <br>
        <c:if test="${not empty searchMessage}">
            <p>${searchMessage}</p>
        </c:if>
        <c:if test="${not empty latestContacts}">
            <table id="latestContacts">
                <tr>
                    <th>Name</th>
                    <th>GSM</th>
                    <th>Email</th>
                </tr>
                <c:forEach var="contact" items="${latestContacts}">
                    <tr>
                        <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                        <td><c:out value="${contact.phoneNumber}"/></td>
                        <td><c:out value="${contact.email}"/></td>
                    </tr>
                </c:forEach>
                <caption>Contacts Overview</caption>
            </table>
        </c:if>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>