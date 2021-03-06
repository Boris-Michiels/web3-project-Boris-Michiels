<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <c:set var="title" value="Admin Page" scope="request"/>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/ContactsTableScript.js" defer></script>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>

    <main>
        <jsp:include page="statusMessage.jsp"/>
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
                <label for="fromContact">From:</label>
                <input type="datetime-local" id="fromContact" onchange="filterContactsTable()">
                <label for="untilContact">Until:</label>
                <input type="datetime-local" id="untilContact" onchange="filterContactsTable()">
                <button onclick="clearContactsFilter()">Clear</button>

                <table id="contactsTable">
                    <tr>
                        <th>Date</th>
                        <th>Hour</th>
                        <th>User Name</th>
                        <th>Contact Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Remove</th>
                    </tr>
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><c:out value="${contact.dateString}"/></td>
                            <td><c:out value="${contact.timeString}"/></td>
                            <td><c:out value="${personMap[contact.userid].fullName}"/></td>
                            <td><c:out value="${contact.fullName}"/></td>
                            <td><c:out value="${contact.email}"/></td>
                            <td><c:out value="${contact.phoneNumber}"/></td>
                            <td>
                                <form method="post" action="Controller?command=RemoveContactConfirmationPage&contactid=${contact.contactid}">
                                    <input type="hidden" name="origin" id="origin" value="${title}">
                                    <p>
                                        <input type="submit" name="remove" id="remove" value="Remove">
                                    </p>
                                </form>
                            </td>
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
                        <th>User Name</th>
                        <th>Date</th>
                    </tr>
                    <c:forEach var="testResult" items="${testResults}">
                        <tr>
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