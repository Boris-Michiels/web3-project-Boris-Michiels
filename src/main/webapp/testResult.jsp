<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Test Result</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Test Result"/>
    </jsp:include>

    <main>
        <h3>Register Positive Test Result</h3>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?command=RegisterTestResult" novalidate>
            <p>
                <label for="date">Date</label>
                <input class="form-group ${dateClass}" type="date" id="date" name="date" required>
            </p>
            <p>
                <input type="submit" id="add" value="Register Positive Test Result">
            </p>
        </form>

        <h3>Test Results</h3>
        <br>
        <c:choose>
            <c:when test="${empty testResults}">
                <p>You haven't registered any positive tests yet.</p>
                <br>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Contacts since</th>
                    </tr>
                    <c:forEach var="testResult" items="${testResults}">
                        <tr>
                            <td><c:out value="${testResult.dateString}"/></td>
                            <td>
                                <form method="post" action="Controller?command=TestResultInfo&testResultid=${testResult.testResultid}">
                                    <p>
                                        <input type="submit" name="contactsSince" id="contactsSince" value="Contacts since">
                                    </p>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <caption>Test Results Overview</caption>
                </table>
            </c:otherwise>
        </c:choose>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>