<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Home"/>
    </jsp:include>

    <main>
        <c:if test="${not empty statusMessage}">
            <div class="${messageClass}">
                <p>${statusMessage}</p>
            </div>
            <c:remove var="statusMessage" scope="session"/>
        </c:if>
        <p>
            Sed ut perspiciatis unde omnis iste natus error sit
            voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
            ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
            dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
            aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
            qui ratione voluptatem sequi nesciunt.
        </p>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>