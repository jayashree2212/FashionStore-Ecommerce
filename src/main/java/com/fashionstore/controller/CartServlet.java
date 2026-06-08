package com.fashionstore.controller;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO = new CartDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("view".equals(action)) {
            viewCart(request, response);
        } else if ("remove".equals(action)) {
            removeItem(request, response);
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addToCart(request, response);
        } else if ("update".equals(action)) {
            updateCart(request, response);
        } else {
            response.sendRedirect("home");
        }
    }

    // ================= ADD =================
    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartItem item = new CartItem();
        item.setUserId(user.getUserId());
        item.setProductId(productId);
        item.setSize(size);
        item.setQuantity(quantity);

        cartDAO.addToCart(item);

        response.sendRedirect("cart?action=view");
    }

    // ================= VIEW =================
    private void viewCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        List<CartItem> cartItems = cartDAO.getCartByUserId(user.getUserId());

        request.setAttribute("cartItems", cartItems);

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
               .forward(request, response);
    }

    // ================= UPDATE =================
    private void updateCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartItem item = new CartItem();
        item.setCartId(cartId);
        item.setQuantity(quantity);

        cartDAO.updateCartItem(item);

        response.sendRedirect("cart?action=view");
    }

    // ================= REMOVE =================
    private void removeItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));

        cartDAO.removeCartItem(cartId);

        response.sendRedirect("cart?action=view");
    }
}