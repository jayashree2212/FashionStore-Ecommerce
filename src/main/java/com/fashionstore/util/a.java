package com.fashionstore.util;

import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

import java.util.List;

public class a {

    public static void main(String[] args) {

        // =========================
        // 1. USER DAO TEST
        // =========================
        UserDAO userDAO = new UserDAOImpl();

        System.out.println("===== USER TEST =====");
        User user = userDAO.getUserByEmail("john@example.com");
        if (user != null) {
            System.out.println("User Found: " + user.getName());
        } else {
            System.out.println("User NOT Found");
        }

        // =========================
        // 2. PRODUCT DAO TEST
        // =========================
        ProductDAO productDAO = new ProductDAOImpl();

        System.out.println("\n===== PRODUCT TEST =====");
        List<Product> products = productDAO.getAllProducts();

        int testProductId = 0;

        for (Product p : products) {
            System.out.println(p.getProductId() + " - " + p.getName() + " - ₹" + p.getPrice());

            if (testProductId == 0) {
                testProductId = p.getProductId();
            }
        }

        // =========================
        // 3. CATEGORY DAO TEST
        // =========================
        CategoryDAO categoryDAO = new CategoryDAOImpl();

        System.out.println("\n===== CATEGORY TEST =====");

        // 3.1 Get All Categories
        List<Category> categories = categoryDAO.getAllCategories();
        for (Category c : categories) {
            System.out.println(c.getCategoryId() + " - " + c.getName());
        }

        // 3.2 Get Category By ID
        System.out.println("\nGet Category By ID (1):");
        Category cat = categoryDAO.getCategoryById(1);
        if (cat != null) {
            System.out.println(cat.getCategoryId() + " - " + cat.getName());
        }

        // 3.3 Add Category
        System.out.println("\nAdding Category: 'TestCategory'");
        Category newCat = new Category();
        newCat.setName("TestCategory");

        boolean added = categoryDAO.addCategory(newCat);
        System.out.println("Added: " + added);

        // 3.4 Update Category
        System.out.println("\nUpdating Category ID 1 → 'Men Updated'");
        Category updateCat = new Category();
        updateCat.setCategoryId(1);
        updateCat.setName("Men Updated");

        boolean updated = categoryDAO.updateCategory(updateCat);
        System.out.println("Updated: " + updated);

        // 3.5 Delete Category (optional testing)
        System.out.println("\nDeleting Category (TestCategory)");
        // ⚠️ You may need to find ID manually if needed

        // =========================
        // 4. PRODUCT SIZE TEST
        // =========================
        ProductSizeDAO sizeDAO = new ProductSizeDAOImpl();

        System.out.println("\n===== PRODUCT SIZE TEST =====");

        List<ProductSize> sizes = sizeDAO.getSizesByProductId(testProductId);

        if (sizes.isEmpty()) {
            System.out.println("No sizes found for product ID: " + testProductId);
        } else {
            for (ProductSize s : sizes) {
                System.out.println("Size: " + s.getSize() + " | Stock: " + s.getStock());
            }
        }

        // =========================
        // 5. CART TEST
        // =========================
        CartDAO cartDAO = new CartDAOImpl();

        System.out.println("\n===== CART TEST =====");
        List<CartItem> cartItems = cartDAO.getCartByUserId(1);

        for (CartItem c : cartItems) {
            System.out.println("Product ID: " + c.getProductId() +
                    " | Size: " + c.getSize() +
                    " | Qty: " + c.getQuantity());
        }

        // =========================
        // 6. ORDER TEST
        // =========================
        OrderDAO orderDAO = new OrderDAOImpl();

        System.out.println("\n===== ORDER TEST =====");
        List<Order> orders = orderDAO.getOrdersByUserId(1);

        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId() +
                    " | Amount: " + o.getTotalAmount() +
                    " | Status: " + o.getStatus());
        }

        // =========================
        // 7. ORDER ITEMS TEST
        // =========================
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        System.out.println("\n===== ORDER ITEMS TEST =====");
        List<OrderItem> items = orderItemDAO.getItemsByOrderId(1);

        for (OrderItem i : items) {
            System.out.println("Product ID: " + i.getProductId() +
                    " | Size: " + i.getSize() +
                    " | Qty: " + i.getQuantity());
        }

        System.out.println("\n===== TEST COMPLETED =====");
    }
}