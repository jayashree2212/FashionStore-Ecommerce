package com.fashionstore.controller;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.model.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = 1; // later from session

        List<Order> orders = orderDAO.getOrdersByUserId(userId);

        request.setAttribute("orders", orders);

        RequestDispatcher rd =
                request.getRequestDispatcher("/WEB-INF/views/orders.jsp");

        rd.forward(request, response);
    }
}