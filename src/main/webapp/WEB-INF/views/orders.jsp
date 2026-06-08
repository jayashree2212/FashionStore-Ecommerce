<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Orders</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <style>

        body{
            font-family: Arial, sans-serif;
            background:#f5f5f5;
        }

        .orders-container{
            width:80%;
            margin:40px auto;
            background:white;
            padding:30px;
            border-radius:10px;
            box-shadow:0 2px 10px rgba(0,0,0,0.1);
        }

        h2{
            text-align:center;
            margin-bottom:25px;
        }

        table{
            width:100%;
            border-collapse:collapse;
        }

        th, td{
            padding:14px;
            border-bottom:1px solid #ddd;
            text-align:center;
        }

        th{
            background:#6a5af9;
            color:white;
        }

        .status{
            color:green;
            font-weight:bold;
        }

    </style>
</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<div class="orders-container">

    <h2>My Orders</h2>

    <table>

        <tr>
            <th>Order ID</th>
            <th>Total Amount</th>
            <th>Status</th>
        </tr>

        <c:forEach var="o" items="${orders}">

            <tr>
                <td>${o.orderId}</td>
                <td>₹ ${o.totalAmount}</td>
                <td class="status">${o.status}</td>
            </tr>

        </c:forEach>

    </table>

</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>