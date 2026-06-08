package com.fashionstore.dao;

import com.fashionstore.model.OrderItem;
import java.util.List;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem item);

    List<OrderItem> getItemsByOrderId(int orderId);
}