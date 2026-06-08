package com.fashionstore.dao.impl;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int createOrder(Order order) {
        int orderId = 0;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO orders (user_id, total_amount, status, order_date) VALUES (?, ?, ?, NOW())";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setString(3, order.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM orders WHERE order_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getTimestamp("order_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        boolean result = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE orders SET status=? WHERE order_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, orderId);

            int rows = ps.executeUpdate();
            result = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}