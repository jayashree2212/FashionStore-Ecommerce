<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.fashionstore.model.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fashion Store</title>

    <!-- CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/home.css">
</head>

<body>
<%@ include file="partials/navbar.jsp" %>
<!-- NAVBAR -->


<!-- CATEGORY FILTER -->
<div class="categories">

    <a href="<%=request.getContextPath()%>/home">All</a>

    <%
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) request.getAttribute("categories");

        if (categories != null) {
            for (Category c : categories) {
    %>
        <a href="home?categoryId=<%=c.getCategoryId()%>">
            <%=c.getName()%>
        </a>
    <%
            }
        }
    %>

</div>

<!-- PRODUCT LIST -->
<div class="product-container">

    <%
        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>) request.getAttribute("products");

        if (products != null && !products.isEmpty()) {
            for (Product p : products) {
    %>

    <div class="product-card">

        <img src="<%=request.getContextPath()%>/assets/images/<%=p.getImageUrl()%>" alt="product">

        <h3><%=p.getName()%></h3>

        <p class="price">&#8377; <%=p.getPrice()%></p>

        <a href="product-details?id=<%=p.getProductId()%>" class="btn">View</a>

    </div>

    <%
            }
        } else {
    %>

        <p style="padding:20px;">No products available</p>

    <%
        }
    %>

</div>

<!-- FOOTER -->
<jsp:include page="partials/footer.jsp" />

</body>
</html>