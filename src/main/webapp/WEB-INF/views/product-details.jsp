<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.fashionstore.model.Product, com.fashionstore.model.ProductSize" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/product.css">
</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<%
    Product product = (Product) request.getAttribute("product");
    List<ProductSize> sizes = (List<ProductSize>) request.getAttribute("sizes");
%>

<div class="product-details-container">

    <!-- IMAGE -->
    <div class="product-image">
        <img src="<%=request.getContextPath()%>/assets/images/<%=product.getImageUrl()%>" alt="product">
    </div>

    <!-- DETAILS -->
    <div class="product-info">

        <h2><%=product.getName()%></h2>

        <p class="price">&#8377; <%=product.getPrice()%></p>

        <p class="desc"><%=product.getDescription()%></p>

        <!-- FORM START -->
        <form action="<%=request.getContextPath()%>/cart?action=add" method="post">

            <!-- PRODUCT ID -->
            <input type="hidden" name="productId" value="<%=product.getProductId()%>">

            <!-- SIZE -->
            <div class="sizes">
                <h4>Select Size:</h4>

                <select name="size" required>
                    <%
                        if (sizes != null) {
                            for (ProductSize s : sizes) {
                    %>
                        <option value="<%=s.getSize()%>">
                            <%=s.getSize()%>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <br>

            <!-- QUANTITY -->
            <label>Quantity:</label>
            <input type="number" name="quantity" value="1" min="1" required>

            <br><br>

            <!-- ADD TO CART -->
            <button type="submit" class="add-cart-btn">Add to Cart</button>

        </form>
        <!-- FORM END -->

    </div>

</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>