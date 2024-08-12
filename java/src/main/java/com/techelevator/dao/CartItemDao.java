package com.techelevator.dao;

import com.techelevator.model.CartItem;
import java.util.List;

public interface CartItemDao {

    CartItem getCartItemById(int cartItemId);
    CartItem getCartItemByCakeIdAndUserId(int cakeId, int userId);
    List<CartItem> getCartItemsByUserId(int userId);
    CartItem createCartItem(CartItem item, int userId);
    CartItem rejectCartItemByUserId(CartItem cartItem, int userId);
    CartItem cancelCartItemByUserId(CartItem cartItem, int userId);
    CartItem rejectCartItemByCakeId(CartItem cartItem, int cakeId);
    CartItem cancelCartItemByCakeId(CartItem cartItem, int cakeId);

    // New Methods
    CartItem deleteCartItemsByUserId(int userId);
    CartItem deleteCartItemById(int cartItemId);
    CartItem updateCartItem(CartItem cartItem);
    List<CartItem> getAllOrdersForBaker();
}