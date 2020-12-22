<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <c:set var="title" value="Contacts" scope="request"/>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/ContactsTableScript.js" defer></script>
    <script src="scripts/FormValidationScript.js" defer></script>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>

    <main>
        <c:if test="${not empty statusMessage}">
            <div id="statusMessage" class="${messageClass}">
                <p>${statusMessage}</p>
            </div>
            <c:remove var="statusMessage" scope="session"/>
        </c:if>
        <h3>Contacts overview</h3>
        <c:choose>
            <c:when test="${empty contacts}">
                <p>You haven't added any contacts yet.</p>
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
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Remove</th>
                    </tr>
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><c:out value="${contact.dateString}"/></td>
                            <td><c:out value="${contact.timeString}"/></td>
                            <td><c:out value="${contact.fullName}"/></td>
                            <td><c:out value="${contact.email}"/></td>
                            <td><c:out value="${contact.phoneNumber}"/></td>
                            <td>
                                <form method="post" action="Controller?command=RemoveContactConfirmationPage&contactid=${contact.contactid}">
                                    <input type="hidden" name="title" id="title" value="${title}">
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

        <h3>Add a contact</h3>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form name="contactForm" method="post" onsubmit="return validateContactForm()" action="Controller?command=AddContact" novalidate>
            <p>
                <label for="firstName">First Name</label>
                <input class="form-group ${firstNameClass}" type="text" id="firstName" name="firstName" value="${fn:escapeXml(firstNamePreviousValue)}" required>
            </p>
            <p>
                <label for="lastName">Last Name</label>
                <input class="form-group ${lastNameClass}" type="text" id="lastName" name="lastName" value="${fn:escapeXml(lastNamePreviousValue)}" required>
            </p>
            <p>
                <label for="email">Email</label>
                <input class="form-group ${emailClass}" type="email" id="email" name="email" value="${fn:escapeXml(emailPreviousValue)}" required>
            </p>
            <p>
                <label for="phoneNumber">Phone number</label>
                <input class="form-group ${phoneNumberClass}" type="tel" id="phoneNumber" name="phoneNumber" value="${fn:escapeXml(phoneNumberPreviousValue)}" required>
            </p>
            <p>
                <label for="dateTime">Date and Time</label>
                <input class="form-group ${dateTimeClass}" type="datetime-local" id="dateTime" name="dateTime" value="${fn:escapeXml(dateTimePreviousValue)}" required>
            </p>
            <p>
                <input type="submit" id="add" value="Add">
            </p>
        </form>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>