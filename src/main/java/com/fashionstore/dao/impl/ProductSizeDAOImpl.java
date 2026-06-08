package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductSizeDAO;
import com.fashionstore.model.ProductSize;
import com.fashionstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductSizeDAOImpl implements ProductSizeDAO {

    @Override
    public boolean addProductSize(ProductSize size) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO product_sizes (product_id, size, stock) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, size.getProductId());
            ps.setString(2, size.getSize());
            ps.setInt(3, size.getStock());

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<ProductSize> getSizesByProductId(int productId) {
        List<ProductSize> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM product_sizes WHERE product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductSize size = new ProductSize();

                size.setSizeId(rs.getInt("size_id"));
                size.setProductId(rs.getInt("product_id"));
                size.setSize(rs.getString("size"));
                size.setStock(rs.getInt("stock"));

                list.add(size);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateStock(int sizeId, int stock) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE product_sizes SET stock=? WHERE size_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, stock);
            ps.setInt(2, sizeId);

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteSize(int sizeId) {
        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "DELETE FROM product_sizes WHERE size_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, sizeId);

            int rows = ps.executeUpdate();
            status = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}