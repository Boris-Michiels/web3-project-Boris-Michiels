<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Profile"/>
    </jsp:include>

    <main>
        <c:choose>
            <c:when test="${empty person}">
                <c:if test="${not empty message}">
                    <div class="alert-danger">
                        <p>${message}</p>
                    </div>
                </c:if>

                <form method="post" action="Controller?command=LogIn" novalidate>
                    <p>
                        <label for="userid">User id (e-mail)</label>
                        <input type="text" id="userid" name="userid" value="${useridPreviousValue}" required>
                    </p>
                    <p>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" required>
                    </p>
                    <p>
                        <input type="submit" id="logIn" value="Log In">
                    </p>
                </form>
            </c:when>
            <c:otherwise>
                <h3>Welcome ${person.firstName}</h3>
                <br>
                <p>Manage your account</p>

                <form method="post" action="Controller?command=LogOut" novalidate>
                    <p>
                        <input type="submit" id="logOut" value="Log Out">
                    </p>
                </form>

                <c:if test="${not empty newPMessage}">
                    <div class="alert-danger">
                        <p>${newPMessage}</p>
                    </div>
                </c:if>

                <form method="post" action="Controller?command=ChangePassword" novalidate>
                    <p>
                        <label for="newPassword">New Password</label>
                        <input type="text" id="newPassword" name="newPassword" required>
                    </p>
                    <p>
                        <input type="submit" id="changePassword" value="Change Password">
                    </p>
                </form>

                <form method="post" action="Controller?command=RemoveConfirmation" novalidate>
                    <p>
                        <input type="submit" id="removeConfirmation" value="Remove account">
                    </p>
                </form>
            </c:otherwise>
        </c:choose>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>