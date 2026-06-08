package com.fashionstore.controller;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = productDAO.getAllProducts();

        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}