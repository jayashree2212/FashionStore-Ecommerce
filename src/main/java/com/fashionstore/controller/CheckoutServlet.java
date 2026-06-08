package com.fashionstore.controller;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.dao.impl.OrderItemDAOImpl;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO = new CartDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = 1; // (later from session)

        List<CartItem> cartItems = cartDAO.getCartByUserId(userId);

        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("total", total);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/checkout.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = 1;

        List<CartItem> cartItems = cartDAO.getCartByUserId(userId);

        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        // 1. Create Order
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setStatus("PLACED");

        int orderId = orderDAO.createOrder(order);

        // 2. Insert Order Items
        for (CartItem c : cartItems) {

            OrderItem oi = new OrderItem();

            oi.setOrderId(orderId);
            oi.setProductId(c.getProductId());
            oi.setSize(c.getSize());
            oi.setQuantity(c.getQuantity());

            orderItemDAO.addOrderItem(oi);
        }

        // 3. Clear Cart
        cartDAO.clearCart(userId);

        // 4. Redirect
        request.getRequestDispatcher("/WEB-INF/views/order-success.jsp")
        .forward(request, response);
    }
}