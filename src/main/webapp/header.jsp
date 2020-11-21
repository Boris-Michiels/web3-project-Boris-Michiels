<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1><span>Restaurant Contact Tracer</span></h1>

    <nav>
        <ul>
            <li ${param.title == 'Home' ? "id=actual" : ""}><a href="Controller?command=Home">Home</a></li>
            <c:if test="${not empty person}">
                <c:if test="${person.role == 'admin'}">
                    <li ${param.title == 'User Overview' ? "id=actual" : ""}><a href="Controller?command=Overview">Overview</a></li>
                </c:if>
                <li ${param.title == 'Contacts' ? "id=actual" : ""}><a href="Controller?command=Contacts">Contacts</a></li>
                <li ${param.title == 'Register Test Result' ? "id=actual" : ""}><a href="Controller?command=RegisterTestResultPage">Register Test Result</a></li>
            </c:if>
            <li ${param.title == 'Profile' ? "id=actual" : ""}><a href="Controller?command=Profile">Profile</a></li>
        </ul>
    </nav>

    <h2>${param.title}</h2>
</header>