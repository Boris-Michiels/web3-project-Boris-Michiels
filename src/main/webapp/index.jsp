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
        <h1><span>XXX</span></h1>

        <nav>
            <ul>
                <li id="actual"><a href="Controller">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>

        <h2>Home</h2>
    </header>

    <main> Sed ut perspiciatis unde omnis iste natus error sit
        voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
        ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
        dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
        aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
        qui ratione voluptatem sequi nesciunt.

        <br>

        <c:choose>
            <c:when test="${empty person}">
                <c:if test="${not empty loginfail}">
                    <div class="alert-danger">
                        <p>${loginfail}</p>
                    </div>
                </c:if>

                <form method="post" action="Controller?command=LogIn" novalidate>
                    <p>
                        <label for="userid">User id (e-mail)</label>
                        <input class="form-group ${useridClass}" type="text" id="userid" name="userid" value="${useridPreviousValue}" required>
                    </p>
                    <p>
                        <label for="password">Password</label>
                        <input class="form-group ${passwordClass}" type="password" id="password" name="password" value="${passwordPreviousValue}" required>
                    </p>
                    <p>
                        <input type="submit" id="logIn" value="Log In">
                    </p>
                </form>
            </c:when>
            <c:otherwise>
                <p id="loggedin">Welcome ${person.firstName}!</p>

                <form method="post" action="Controller?command=LogOut" novalidate>
                    <p>
                        <input type="submit" id="logOut" value="Log Out">
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