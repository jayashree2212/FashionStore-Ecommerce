<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Checkout</title>
</head>

<body>

<h2 style="text-align:center;">Checkout</h2>

<table border="1" style="width:80%; margin:auto; text-align:center;">

<tr>
    <th>Product</th>
    <th>Size</th>
    <th>Price</th>
    <th>Qty</th>
    <th>Total</th>
</tr>

<c:forEach var="item" items="${cartItems}">
<tr>
    <td>${item.productName}</td>
    <td>${item.size}</td>
    <td>₹ ${item.price}</td>
    <td>${item.quantity}</td>
    <td>₹ ${item.price * item.quantity}</td>
</tr>
</c:forEach>

<tr>
    <td colspan="4"><b>Grand Total</b></td>
    <td><b>₹ ${total}</b></td>
</tr>

</table>

<form action="${pageContext.request.contextPath}/checkout" method="post" style="text-align:center; margin-top:20px;">
    <button type="submit">Place Order</button>
</form>

</body>
</html>
