<%@ page import="com.fashionstore.model.User" %>

<style>
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 30px;
    background: linear-gradient(to right, #6a5af9, #836fff);
    color: white;
}

.logo {
    font-size: 22px;
    font-weight: bold;
}

.nav-links {
    display: flex;
    gap: 20px;
    align-items: center;
}

.nav-links a {
    color: white;
    text-decoration: none;
    font-size: 16px;
    font-weight: 500;
}

.nav-links a:hover {
    text-decoration: underline;
}

.welcome-text{
    font-size:15px;
    font-weight:bold;
}
</style>

<div class="navbar">

    <div class="logo">
        FashionStore
    </div>

    <div class="nav-links">

        <a href="<%=request.getContextPath()%>/home">
            Home
        </a>

        <a href="<%=request.getContextPath()%>/products">
            Products
        </a>

        <a href="<%=request.getContextPath()%>/cart?action=view">
            Cart
        </a>

        <%
            User user = (User) session.getAttribute("user");

            if(user == null){
        %>

            <a href="<%=request.getContextPath()%>/login">
                Login
            </a>

            <a href="<%=request.getContextPath()%>/register">
                Register
            </a>

        <%
            } else {
        %>

            <span class="welcome-text">
                Welcome, <%= user.getName() %>
            </span>

            <a href="<%=request.getContextPath()%>/orders">
                My Orders
            </a>

            <a href="<%=request.getContextPath()%>/logout">
                Logout
            </a>

        <%
            }
        %>

    </div>

</div>