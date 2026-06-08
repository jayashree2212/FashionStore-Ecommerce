package com.fashionstore.controller;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.dao.impl.UserDAOImpl;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            // ✅ Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/home");

        } else {
            response.sendRedirect("login");
        }
    }
}