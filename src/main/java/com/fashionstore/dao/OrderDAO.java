package com.fashionstore.dao;

import com.fashionstore.model.Order;
import java.util.List;

public interface OrderDAO {

    int createOrder(Order order);

    List<Order> getOrdersByUserId(int userId);

    Order getOrderById(int orderId);

    boolean updateOrderStatus(int orderId, String status);
}