<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order Success</title>

    <style>
        body {
            font-family: Arial;
            text-align: center;
            padding-top: 100px;
        }

        .box {
            display: inline-block;
            padding: 40px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background: #f9f9f9;
        }

        .btn {
            margin-top: 20px;
            padding: 10px 20px;
            background: #6a5af9;
            color: white;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="box">
    <h2>✅ Order Placed Successfully!</h2>
    <p>Thank you for shopping with us.</p>

    <a href="<%=request.getContextPath()%>/home" class="btn">
        Go to Home
    </a>
</div>

</body>
</html>