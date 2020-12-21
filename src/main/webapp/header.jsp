<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1><span>Restaurant Contact Tracer</span></h1>

    <nav>
        <ul>
            <li ${param.title == 'Home' ? "class=active" : ""}><a href="Controller?command=HomePage">Home</a></li>
            <c:if test="${not empty person}">
                <li ${param.title == 'Contacts' ? "class=active" : ""}><a href="Controller?command=ContactsPage">Contacts</a></li>
                <li ${param.title == 'Test Result' ? "class=active" : ""}><a href="Controller?command=TestResultPage">Test Result</a></li>
                <li ${param.title == 'Search' ? "class=active" : ""}><a href="Controller?command=SearchPage">Search</a></li>
                <c:if test="${person.role == 'ADMIN'}">
                    <li ${param.title == 'Admin Page' ? "class=active" : ""}><a href="Controller?command=AdminPage">Admin</a></li>
                </c:if>
            </c:if>
            <li ${param.title == 'Profile' ? "class=active" : ""}><a href="Controller?command=ProfilePage">Profile</a></li>
        </ul>
    </nav>

    <h2>${param.title}</h2>
</header>