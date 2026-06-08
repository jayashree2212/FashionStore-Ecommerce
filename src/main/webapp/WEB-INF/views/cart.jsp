<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Cart</title>
</head>

<body>

<h2 style="text-align:center;">My Cart</h2>

<table border="1" style="width:80%; margin:auto; text-align:center;">

<tr>
    <th>Product</th>
    <th>Size</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Total</th>
    <th>Action</th>
</tr>

<c:set var="grandTotal" value="0" />

<c:forEach var="item" items="${cartItems}">

<tr>
    <td>${item.productName}</td>
    <td>${item.size}</td>
    <td>₹ ${item.price}</td>
    <td>${item.quantity}</td>
    <td>₹ ${item.price * item.quantity}</td>

    <td>
        <a href="${pageContext.request.contextPath}/cart?action=remove&cartId=${item.cartId}">
            Remove
        </a>
    </td>
</tr>

<c:set var="grandTotal" value="${grandTotal + (item.price * item.quantity)}" />

</c:forEach>

<tr>
    <td colspan="4"><b>Grand Total</b></td>
    <td colspan="2"><b>₹ ${grandTotal}</b></td>
</tr>

</table>

<!-- ✅ CHECKOUT BUTTON (CORRECT PLACE) -->
<div style="text-align:center; margin-top:20px;">
    <a href="${pageContext.request.contextPath}/checkout">
        <button style="
            padding:10px 20px;
            background:#6a5af9;
            color:white;
            border:none;
            border-radius:5px;
            cursor:pointer;
        ">
            Proceed to Checkout
        </button>
    </a>
</div>

</body>
</html>