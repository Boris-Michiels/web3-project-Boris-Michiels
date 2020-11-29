<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Admin Page</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Admin Page"/>
    </jsp:include>

    <main>
        <h3>User Overview</h3>
        <table>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach var="person" items="${persons}">
                <tr>
                    <td><c:out value="${person.email}"/></td>
                    <td><c:out value="${person.firstName}"/></td>
                    <td><c:out value="${person.lastName}"/></td>
                </tr>
            </c:forEach>
            <caption>Users Overview</caption>
        </table>
        <br>

        <h3>Contacts Overview</h3>
        <c:choose>
            <c:when test="${empty contacts}">
                <p>There have been no contacts yet.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th colspan="2">Added by</th>
                        <th rowspan="2">Date</th>
                        <th rowspan="2">Hour</th>
                        <th rowspan="2">Name</th>
                    </tr>
                    <tr>
                        <th>UserId</th>
                        <th>Name</th>
                    </tr>
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><c:out value="${contact.userid}"/></td>
                            <td><c:out value="${personMap[contact.userid].fullName}"/></td>
                            <td><c:out value="${contact.dateString}"/></td>
                            <td><c:out value="${contact.timeString}"/></td>
                            <td><c:out value="${contact.fullName}"/></td>
                        </tr>
                    </c:forEach>
                    <caption>Contacts Overview</caption>
                </table>
                <br>
            </c:otherwise>
        </c:choose>
        <br>

        <h3>Test Result Overview</h3>
        <c:choose>
            <c:when test="${empty testResults}">
                <p>There have been no positive tests registered yet.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th colspan="2">Added by</th>
                        <th rowspan="2">Date</th>
                    </tr>
                    <tr>
                        <th>UserId</th>
                        <th>Name</th>
                    </tr>
                    <c:forEach var="testResult" items="${testResults}">
                        <tr>
                            <td><c:out value="${testResult.userid}"/></td>
                            <td><c:out value="${personMap[testResult.userid].fullName}"/></td>
                            <td><c:out value="${testResult.dateString}"/></td>
                        </tr>
                    </c:forEach>
                    <caption>Test Result Overview</caption>
                </table>
                <br>
            </c:otherwise>
        </c:choose>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>