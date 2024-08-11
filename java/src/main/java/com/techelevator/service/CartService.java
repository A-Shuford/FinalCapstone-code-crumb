package com.techelevator.service;

import com.techelevator.dao.CakeDao;
import com.techelevator.dao.CartItemDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Cake;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;

@Component
public class CartService {

    private CartItemDao cartItemDao;

    private CakeDao cakeDao;

    private UserDao userDao;
    private TaxService taxService;


    public CartService(CartItemDao cartItemDao, CakeDao cakeDao, UserDao userDao, TaxService taxService) {
        this.cartItemDao = cartItemDao;
        this.cakeDao = cakeDao;
        this.userDao = userDao;
        this.taxService = taxService;
    }

    public Cart getUserCart(Principal principal){
        User user = getUser(principal);
        int userId = user.getId();

        List<CartItem> items = cartItemDao.getCartItemsByUserId(userId);
        Cart cart = new Cart(items);

        List<Cake> cakes = cakeDao.getCakesByUserId(userId);

        for(CartItem item : items){
            item.setCake(findCake(cakes, item.getCakeId()));
        }
        BigDecimal taxRate = taxService.getTaxRate();
        BigDecimal subtotal = cart.getItemSubtotal();
        BigDecimal tax = subtotal.multiply(taxRate).setScale(2, RoundingMode.UP);
        cart.setTax(tax);
        return cart;

    }

    public void clearCart(Principal principal){
        int userId = getUserId(principal);
        cartItemDao.deleteCartItemsByUserId(userId);
    }

    private Cake findCake(List<Cake> cakes, int cakeId){
        for (Cake cake : cakes){
            if(cake.getCakeId() == cakeId){
                return cake;
            }
        }
        return null;
    }

    private int getUserId(Principal principal){
        return userDao.getUserByUsername(principal.getName()).getId();
    }


    private User getUser(Principal principal){
        return userDao.getUserByUsername(principal.getName());
    }

    public CartItem addToCart(Principal principal, CartItem item){

        int userId = getUserId(principal);
        item.setUserId(userId);

        CartItem existingItem = cartItemDao.getCartItemByCakeIdAndUserId(item.getCakeId(), userId);

        if(existingItem == null){

            return cartItemDao.createCartItem(item);
        }else{
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            return cartItemDao.updateCartItem(existingItem);
        }
    }

    public void removeFromCart(Principal principal, int cartItemId){
        cartItemDao.deleteCartItemById(cartItemId);
    }

    public void rejectItemByUserId(CartItem cartItem, int userId){
        cartItemDao.rejectCartItemByUserId(cartItem, userId);
    }

    public CartItem addStandarCake(Principal principal, int cakeId) {
        CartItem item = new CartItem();
        int userId = getUserId(principal);
        item.setUserId(userId);

        CartItem existingItem = cartItemDao.getCartItemByCakeIdAndUserId(cakeId, userId);

        if(existingItem == null){

            return cartItemDao.createCartItem(item);
        }else{
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            return cartItemDao.updateCartItem(existingItem);
        }
    }
}
