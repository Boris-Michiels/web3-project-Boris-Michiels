<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <c:set var="title" value="Something is wrong" scope="request"/>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>

    <main>
        <p>You caused a ${pageContext.exception} on the server.</p>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>