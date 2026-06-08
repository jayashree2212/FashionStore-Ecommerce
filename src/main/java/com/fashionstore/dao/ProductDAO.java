package com.fashionstore.dao;

import com.fashionstore.model.Product;
import java.util.List;

public interface ProductDAO {

    boolean addProduct(Product product);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> filterProducts(int categoryId, double minPrice, double maxPrice);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);
}