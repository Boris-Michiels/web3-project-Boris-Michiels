<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1><span>Restaurant Contact Tracer</span></h1>

    <nav>
        <ul>
            <li ${title == 'Home' ? "class=active" : ""}><a href="Controller?command=HomePage">Home</a></li>
            <c:if test="${not empty person}">
                <li ${title == 'Contacts' ? "class=active" : ""}><a href="Controller?command=ContactsPage">Contacts</a></li>
                <li ${title == 'Test Result' ? "class=active" : ""}><a href="Controller?command=TestResultPage">Test Result</a></li>
                <li ${title == 'Search' ? "class=active" : ""}><a href="Controller?command=SearchPage">Search</a></li>
                <c:if test="${person.role == 'ADMIN'}">
                    <li ${title == 'Admin Page' ? "class=active" : ""}><a href="Controller?command=AdminPage">Admin Page</a></li>
                </c:if>
            </c:if>
            <li ${title == 'Profile' ? "class=active" : ""}><a href="Controller?command=ProfilePage">Profile</a></li>
        </ul>
    </nav>

    <h2>${title}</h2>
</header>