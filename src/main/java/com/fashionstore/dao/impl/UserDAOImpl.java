package com.fashionstore.dao.impl;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.model.User;
import com.fashionstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUser(User user) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO users (name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE users SET name=?, email=?, password=?, phone=?, address=? WHERE user_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setInt(6, user.getUserId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteUser(int userId) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}