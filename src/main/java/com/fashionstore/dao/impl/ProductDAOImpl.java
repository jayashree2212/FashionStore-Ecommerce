package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean addProduct(Product product) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO products (name, description, price, category_id, image_url) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getCategoryId());
            ps.setString(5, product.getImageUrl());

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setImageUrl(rs.getString("image_url"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM products";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setImageUrl(rs.getString("image_url"));

                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM products WHERE category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setImageUrl(rs.getString("image_url"));

                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM products WHERE name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setImageUrl(rs.getString("image_url"));

                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> filterProducts(int categoryId, double minPrice, double maxPrice) {
        List<Product> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM products WHERE category_id = ? AND price BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, categoryId);
            ps.setDouble(2, minPrice);
            ps.setDouble(3, maxPrice);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setImageUrl(rs.getString("image_url"));

                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE products SET name=?, description=?, price=?, category_id=?, image_url=? WHERE product_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getCategoryId());
            ps.setString(5, product.getImageUrl());
            ps.setInt(6, product.getProductId());

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteProduct(int productId) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, productId);

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}