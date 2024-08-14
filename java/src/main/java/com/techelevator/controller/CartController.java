package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cake;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import com.techelevator.service.CakeService;
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
    private CakeService cakeService;

    public CartController(CartService cartService, CakeService cakeService){
        this.cartService = cartService;
        this.cakeService = cakeService;
    }


    @RequestMapping(path="", method = RequestMethod.GET)
    public Cart get(Principal principal){
        return cartService.getUserCart(principal);
    }

    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public void addProduct(@Valid @RequestBody CartItem item, Principal principal){
        try{
            Cake cake = new Cake();
            cake = item.getCake();
            if(item.getCake().getCakeType().equals("Custom")){
                cakeService.addingCustomCake(cake, principal);
                System.out.println("done");
            }else {
                System.out.println("none");
                cartService.addToCart(principal, item); //if it cakeType is custom, add to the cake table.
            }
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


    @RequestMapping(path="/submitorder", method = RequestMethod.PUT )
    public void submittedOrderByUser(@RequestBody List<CartItem> cartItem,Principal principal){
        //receiving the date and time will go directly to the server
        //TODO: mangage the usage of updateCartItemDetails
        try{
            for(CartItem items : cartItem) {
                cartService.submitOrder(items, principal);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }

    /**
     * @param cartItemId The ID of the CartItem to update.
     * @param cartItem The new status for the CartItem.
     * @return The updated CartItem.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{cartItemId}/status")
    public CartItem updateCartItemStatus(@PathVariable int cartItemId, @RequestBody CartItem cartItem, Principal principal) {
        return cartService.updateCartItemStatus(cartItemId, cartItem.getCartItemStatus(), principal);
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
