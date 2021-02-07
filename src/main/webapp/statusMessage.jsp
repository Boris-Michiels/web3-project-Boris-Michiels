<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty statusMessage}">
    <div id="statusMessage" class="${messageClass}">
        <p>${statusMessage}</p>
    </div>
</c:if>