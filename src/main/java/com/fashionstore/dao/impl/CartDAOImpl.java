package com.fashionstore.dao.impl;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    @Override
    public boolean addToCart(CartItem item) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO cart (user_id, product_id, size, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, item.getUserId());
            ps.setInt(2, item.getProductId());
            ps.setString(3, item.getSize());
            ps.setInt(4, item.getQuantity());

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<CartItem> getCartByUserId(int userId) {

        List<CartItem> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT c.*, p.name, p.price " +
                         "FROM cart c JOIN products p ON c.product_id = p.product_id " +
                         "WHERE c.user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartId(rs.getInt("cart_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSize(rs.getString("size"));
                item.setQuantity(rs.getInt("quantity"));

                // NEW FIELDS
                item.setProductName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateCartItem(CartItem item) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE cart SET quantity=? WHERE cart_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, item.getQuantity());
            ps.setInt(2, item.getCartId());

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean removeCartItem(int cartId) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "DELETE FROM cart WHERE cart_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, cartId);

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean clearCart(int userId) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "DELETE FROM cart WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}