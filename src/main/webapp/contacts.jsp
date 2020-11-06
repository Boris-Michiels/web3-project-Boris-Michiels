<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Contacts"/>
    </jsp:include>

    <main>
        <c:if test="${not empty contacts}">
            <table>
                <tr>
                    <th>Date</th>
                    <th>Hour</th>
                    <th>Name</th>
                    <th>Remove</th>
                </tr>
                <c:forEach var="contact" items="${contacts}">
                    <tr>
                        <td>${contact.dateString}</td>
                        <td>${contact.hour}</td>
                        <td>${contact.firstName} ${contact.lastName}</td>
                        <td><a href="Controller?command=RemoveContactConfirmation&firstName=${contact.firstName}&lastName=${contact.lastName}">Remove</a></td>
                    </tr>
                </c:forEach>
                <caption>Contacts Overview</caption>
            </table>
            <br>
        </c:if>

        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?command=AddContact" novalidate>
            <p>
                <input type="hidden" id="userid" name="userid" value="${person.userid}">
            </p>
            <p>
                <label for="firstName">First Name</label>
                <input class="form-group ${firstNameClass}" type="text" id="firstName" name="firstName" value="${firstNamePreviousValue}" required>
            </p>
            <p>
                <label for="lastName">Last Name</label>
                <input class="form-group ${lastNameClass}" type="text" id="lastName" name="lastName" value="${lastNamePreviousValue}" required>
            </p>
            <p>
                <label for="date">Date</label>
                <input class="form-group ${dateClass}" type="date" id="date" name="date" value="${datePreviousValue}" required>
            </p>
            <p>
                <label for="time">Hour</label>
                <input class="form-group ${timeClass}" type="time" id="time" name="time" value="${timePreviousValue}" required>
            </p>
            <p>
                <label for="phoneNumber">Phone number</label>
                <input class="form-group ${phoneNumberClass}" type="tel" id="phoneNumber" name="phoneNumber" value="${phoneNumberPreviousValue}" required>
            </p>
            <p>
                <label for="email">Email</label>
                <input class="form-group ${emailClass}" type="email" id="email" name="email" value="${emailPreviousValue}" required>
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