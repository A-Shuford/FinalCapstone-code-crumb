package com.techelevator.dao;

import com.techelevator.model.CartItem;
import java.util.List;

public interface CartItemDao {

    CartItem getCartItemById(int cartItemId);
    CartItem getCartItemByCakeIdAndUserId(int cakeId, int userId);
    List<CartItem> getCartItemsByUserId(int userId);
    CartItem createCartItem(CartItem item);
    CartItem updateCartItem(CartItem item);
    String rejectCartItemById(int cartItemId);
    String cancelCartItemById (int cartItemId);
    String rejectCartItemsByUserId(int userId);
    String cancelCartItemsByUserId(int userId);

}
