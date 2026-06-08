package com.fashionstore.dao.impl;

import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.model.OrderItem;
import com.fashionstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public boolean addOrderItem(OrderItem item) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO order_items (order_id, product_id, size, quantity, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setString(3, item.getSize());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, item.getPrice());

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM order_items WHERE order_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSize(rs.getString("size"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}