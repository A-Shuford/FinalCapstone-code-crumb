package com.techelevator.service;

import com.techelevator.dao.CakeDao;
import com.techelevator.dao.CartItemDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Cake;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.security.Principal;
@Component
public class CakeService {


    private CartItemDao cartItemDao;

    private CakeDao cakeDao;

    private UserDao userDao;

    private CartService cartService;

    public CakeService(CartItemDao cartItemDao, CakeDao cakeDao, UserDao userDao, CartService cartService) {
        this.cartItemDao = cartItemDao;
        this.cakeDao = cakeDao;
        this.userDao = userDao;
        this.cartService = cartService;
    }

    public Cake addingCustomCake(Cake cake, Principal principal) {
        Cake newCake = cakeDao.createNewCake(cake);
        CartItem cartItem = new CartItem();
        cartItem.setCake(newCake);
        cartService.addToCart(principal, cartItem);
        return newCake;
    }

    private int getUserId(Principal principal){
        return userDao.getUserByUsername(principal.getName()).getId();
    }

    private User getUser(Principal principal){
        return userDao.getUserByUsername(principal.getName());
    }



}
