<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Restaurant Contact Tracer</span></h1>

        <jsp:include page="nav.jsp">
            <jsp:param name="page" value="profile"/>
        </jsp:include>

        <h2>Profile</h2>
    </header>

    <main>
        <c:choose>
            <c:when test="${empty person}">
                <c:if test="${not empty loginFail}">
                    <div class="alert-danger">
                        <p>${loginFail}</p>
                    </div>
                </c:if>

                <c:if test="${not empty removed}">
                    <div class="alert-danger">
                        <p>${removed}</p>
                    </div>
                </c:if>

                <form method="post" action="Controller?command=LogIn" novalidate>
                    <p>
                        <label for="userid">User id (e-mail)</label>
                        <input type="text" id="userid" name="userid" required>
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
                <p id="loggedin">Welcome ${person.firstName}!</p>
                <br>
                <p>Manage your account!</p>

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

                <form method="post" action="removeConfirmation.jsp" novalidate>
                    <p>
                        <input type="submit" id="removeConfirmation" value="Remove account">
                    </p>
                </form>
            </c:otherwise>
        </c:choose>
    </main>

    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>