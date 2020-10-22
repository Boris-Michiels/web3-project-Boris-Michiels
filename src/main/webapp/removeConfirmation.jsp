<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Restaurant Contact Tracer</span></h1>

        <jsp:include page="nav.jsp">
            <jsp:param name="page" value="profile"/>
        </jsp:include>

        <h2>Remove account?</h2>
    </header>

    <main>
        <p>Are you sure you want to remove your account? This action can not be undone.</p>

        <form method="post" action="Controller?command=Remove" novalidate>
            <p>
                <input type="submit" name="confirmation" id="noRemove" value="Don't remove">
            </p>
            <p>
                <input type="submit" name="confirmation" id="remove" value="Remove">
            </p>
        </form>
    </main>

    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>