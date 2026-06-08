<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>

<body>

<%@ include file="partials/navbar.jsp" %>

<div class="auth-container">

    <div class="auth-box">

        <h2>Login</h2>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <input type="email" name="email" placeholder="Email" required>

            <input type="password" name="password" placeholder="Password" required>

            <button type="submit">Login</button>

        </form>

        <p>
            Don’t have an account?
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </p>

    </div>

</div>

</body>
</html>