<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <c:set var="title" value="${origin}" scope="request"/>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>

    <main>
        <h3>Remove contact?</h3>
        <br>
        <p>Are you sure you want to remove this contact? This action can not be undone.</p>
        <p>Contact name: <c:out value="${contact.firstName} ${contact.lastName}"/></p>
        <p>Contact date: <c:out value="${contact.dateString}"/></p>
        <p>Contact time: <c:out value="${contact.timeString}"/></p>

        <form method="post" action="Controller?command=RemoveContact" novalidate>
            <input type="hidden" name="origin" id="origin" value="${title}">
            <input type="hidden" id="contactid" name="contactid" value="${contact.contactid}">
            <p>
                <input type="submit" name="confirmation" id="noRemove" value="Don't remove">
            </p>
            <p>
                <input type="submit" name="confirmation" id="remove" value="Remove">
            </p>
        </form>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>