package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import com.techelevator.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.annotation.Native;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cartitem")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }


    @RequestMapping(path="", method = RequestMethod.GET)
    public Cart get(Principal principal){
        return cartService.getUserCart(principal);
    }

    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public CartItem addProduct(@Valid @RequestBody CartItem item, Principal principal){
        try{
            return cartService.addToCart(principal, item);
        }catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(path = "/standardToCart", method = RequestMethod.POST)
    public CartItem addingStandardCake(@Validated int cakeId, Principal principal){
        try{
            return cartService.addStandardCake(principal, cakeId);
        }catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(path = "/cakes/{cakeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable int cakeId, Principal principal){
        try{
            cartService.removeFromCart(principal, cakeId);

        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clear(Principal principal){
        try{
            cartService.clearCart(principal);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }


    }

    /**
     * Get all orders ONLY accessible by admin
     * @return List of all CartItems.
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<CartItem> getAllOrdersForBaker() {
        return cartService.getAllOrders();
    }

    /**
     * @param cartItemId The ID of the CartItem to update.
     * @param newStatus  The new status for the CartItem.
     * @return The updated CartItem.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/items/{cartItemId}/status")
    public CartItem updateCartItemStatus(@PathVariable int cartItemId, @RequestBody String newStatus) {
        return cartService.updateCartItemStatus(cartItemId, newStatus);
    }


   /* @RequestMapping(path = "", method = RequestMethod.PUT)
    public CartItem rejectingCartItemByUserId(){
        try{
            return cartService.rejectItemByUserId();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
*/





}
