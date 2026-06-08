<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>

<body>

<%@ include file="partials/navbar.jsp" %>

<div class="auth-container">

    <div class="auth-box">

        <h2>Register</h2>

        <form action="${pageContext.request.contextPath}/register" method="post">

            <input type="text" name="name" placeholder="Full Name" required>

            <input type="email" name="email" placeholder="Email" required>

            <input type="password" name="password" placeholder="Password" required>

            <input type="text" name="phone" placeholder="Phone">

            <input type="text" name="address" placeholder="Address">

            <button type="submit">Register</button>

        </form>

        <p>
            Already have an account?
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </p>

    </div>

</div>

</body>
</html>