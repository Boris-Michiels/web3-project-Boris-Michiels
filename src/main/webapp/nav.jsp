<nav>
    <ul>
        <li ${param.page == 'home' ? "id=actual" : ""}><a href="Controller?command=Home">Home</a></li>
        <li ${param.page == 'overview' ? "id=actual" : ""}><a href="Controller?command=Overview">Overview</a></li>
        <li ${param.page == 'register' ? "id=actual" : ""}><a href="Controller?command=Register">Register</a></li>
        <li ${param.page == 'profile' ? "id=actual" : ""}><a href="Controller?command=Profile">Profile</a></li>
    </ul>
</nav>