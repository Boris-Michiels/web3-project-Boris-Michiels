<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <c:set var="title" value="Profile" scope="request"/>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>

    <main>
        <h3>Delete Account?</h3>
        <br>
        <p>Are you sure you want to delete your account? This action can not be undone.</p>

        <form method="post" action="Controller?command=Delete" novalidate>
            <p>
                <input type="submit" name="confirmation" id="noDelete" value="Don't delete">
            </p>
            <p>
                <input type="submit" name="confirmation" id="delete" value="Delete">
            </p>
        </form>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>