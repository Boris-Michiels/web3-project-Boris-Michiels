<nav>
    <ul>
        <li ${param.page == 'home' ? "id=actual" : ""}><a href="index.jsp">Home</a></li>
        <li ${param.page == 'overview' ? "id=actual" : ""}><a href="Controller?command=Overview">Overview</a></li>
        <li ${param.page == 'register' ? "id=actual" : ""}><a href="register.jsp">Register</a></li>
        <li ${param.page == 'profile' ? "id=actual" : ""}><a href="profile.jsp">Profile</a></li>
    </ul>
</nav>