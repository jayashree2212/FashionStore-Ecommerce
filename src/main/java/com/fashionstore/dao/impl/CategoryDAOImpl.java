package com.fashionstore.dao.impl;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public boolean addCategory(Category category) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO categories (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, category.getName());

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
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM categories";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));

                list.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Category category = null;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM categories WHERE category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public boolean updateCategory(Category category) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE categories SET name=? WHERE category_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, category.getName());
            ps.setInt(2, category.getCategoryId());

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
    public boolean deleteCategory(int categoryId) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "DELETE FROM categories WHERE category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, categoryId);

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