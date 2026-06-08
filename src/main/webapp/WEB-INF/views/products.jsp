<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>

<%
List<Product> products =
(List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Products</title>

<link rel="stylesheet"
href="<%=request.getContextPath()%>/assets/css/product.css">

</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<h1 style="text-align:center; margin-top:30px;">
    All Products
</h1>

<div class="products-container">

<%
if(products != null){

    for(Product product : products){
%>

    <div class="product-card">

        <img
        class="product-image"
        src="<%=request.getContextPath()%>/assets/images/<%=product.getImageUrl()%>"
        alt="product">

        <h3 class="product-name">
            <%=product.getName()%>
        </h3>

        <p class="product-price">
            ₹ <%=product.getPrice()%>
        </p>

        <a class="view-btn"
        href="<%=request.getContextPath()%>/product-details?id=<%=product.getProductId()%>">

            View Details

        </a>

    </div>

<%
    }
}
%>

</div>

</body>
</html>