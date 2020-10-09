<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>XXX</span></h1>

        <jsp:include page="nav.jsp">
            <jsp:param name="page" value="register"/>
        </jsp:include>

        <h2>Register</h2>
    </header>

    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?command=Add" novalidate>
            <p>
                <label for="userid">User id</label>
                <input class="form-group ${useridClass}" type="text" id="userid" name="userid" value="${useridPreviousValue}" required>
            </p>
            <p>
                <label for="firstName">First Name</label>
                <input class="form-group ${firstNameClass}" type="text" id="firstName" name="firstName" value="${firstNamePreviousValue}" required value="">
            </p>
            <p>
                <label for="lastName">Last Name</label>
                <input class="form-group ${lastNameClass}" type="text" id="lastName" name="lastName" value="${lastNamePreviousValue}" required>
            </p>
            <p>
                <label for="email">Email</label>
                <input class="form-group ${emailClass}" type="email" id="email" name="email" value="${emailPreviousValue}" required>
            </p>
            <p>
                <label for="password">Password</label>
                <input class="form-group ${passwordClass}" type="password" id="password" name="password" value="${passwordPreviousValue}" required>
            </p>
            <p>
                <input type="submit" id="signUp" value="Sign Up">
            </p>
        </form>
    </main>

    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>