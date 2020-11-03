<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <h1><span>Restaurant Contact Tracer</span></h1>

    <nav>
        <ul>
            <li ${param.title == 'Home' ? "id=actual" : ""}><a href="Controller?command=Home">Home</a></li>
            <li ${param.title == 'User Overview' ? "id=actual" : ""}><a href="Controller?command=Overview">Overview</a></li>
            <li ${param.title == 'Register' ? "id=actual" : ""}><a href="Controller?command=Register">Register</a></li>
            <li ${param.title == 'Profile' ? "id=actual" : ""}><a href="Controller?command=Profile">Profile</a></li>
        </ul>
    </nav>

    <h2>${param.title}</h2>
</header>