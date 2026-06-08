package com.fashionstore.controller;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.model.Product;
import com.fashionstore.model.Category;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO = new ProductDAOImpl();
    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String categoryIdParam = request.getParameter("categoryId");

            List<Product> products;

            if (categoryIdParam != null && !categoryIdParam.isEmpty()) {

                int categoryId = Integer.parseInt(categoryIdParam);

                products = productDAO.getProductsByCategory(categoryId);

            } else {

                products = productDAO.getAllProducts();
            }

            List<Category> categories = categoryDAO.getAllCategories();

            request.setAttribute("products", products);
            request.setAttribute("categories", categories);

            RequestDispatcher rd =
                    request.getRequestDispatcher("/WEB-INF/views/home.jsp");

            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}