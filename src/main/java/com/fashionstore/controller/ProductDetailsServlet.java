package com.fashionstore.controller;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.ProductSizeDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.impl.ProductSizeDAOImpl;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductSize;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductSizeDAO sizeDAO = new ProductSizeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Get product ID from URL
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect("home");
                return;
            }

            int productId = Integer.parseInt(idParam);

            // 2. Fetch product
            Product product = productDAO.getProductById(productId);

            // 3. Fetch sizes
            List<ProductSize> sizes = sizeDAO.getSizesByProductId(productId);

            if (product == null) {
                response.sendRedirect("home");
                return;
            }

            // 4. Send data to JSP
            request.setAttribute("product", product);
            request.setAttribute("sizes", sizes);

            // 5. Forward to JSP
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/product-details.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home");
        }
    }
}