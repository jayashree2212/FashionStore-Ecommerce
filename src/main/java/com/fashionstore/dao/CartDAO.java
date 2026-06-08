package com.fashionstore.dao;

import com.fashionstore.model.CartItem;
import java.util.List;

public interface CartDAO {

    boolean addToCart(CartItem item);

    List<CartItem> getCartByUserId(int userId);

    boolean updateCartItem(CartItem item);

    boolean removeCartItem(int cartId);

    boolean clearCart(int userId);
}