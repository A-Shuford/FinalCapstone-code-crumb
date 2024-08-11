package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CartItem {
    private int cartItemId;
    private int userId;
    @Positive
    private int cakeId; //foreign key in "cart/order"
    private Cake cake; //references cake table in database


    private LocalDate pickupDate;

    private LocalTime pickupTime;

    //The @Notiation must be taken out

    //ADDED
    private int quantity;



    private String cartItemStatus; //this is from the INNER JOIN to get name, not id

    public static final String CART_STATUS_PENDING = "Pending";
    public static final String CART_STATUS_REJECTED = "Rejected by bakery";
    public static final String CART_STATUS_CANCELLED = "Cancelled by customer";
    public static final String CART_STATUS_READY_PU = "Ready for pick-up";
    public static final String CART_STATUS_COMPLETED = "Order Completed";

    public CartItem() {
    }

    public CartItem(int cartItemId, int userId, int cakeId, String cartItemStatus) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.cakeId = cakeId;
        this.cartItemStatus = cartItemStatus;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }
    public String getCartItemStatus() {
        return cartItemStatus;
    }

    public void setCartItemStatus(String cartItemStatus) {
        this.cartItemStatus = cartItemStatus;
    }

    public boolean isPending(){
        return CART_STATUS_PENDING.equals(this.cartItemStatus);
    }

    public boolean isRejected(){
        return CART_STATUS_REJECTED.equals(this.cartItemStatus);
    }
    public boolean isCancelled(){
        return CART_STATUS_CANCELLED.equals(this.cartItemStatus);
    }
    public boolean isReadyPu(){
        return CART_STATUS_READY_PU.equals(this.cartItemStatus);
    }
    public boolean isCompleted(){
        return CART_STATUS_COMPLETED.equals(this.cartItemStatus);
    }

    public void confirmPickup(){
        if (isPending()){
            //only pending orders can be changed to pick-up
            cartItemStatus = CART_STATUS_READY_PU;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " +
                    cartItemStatus+ " to " + CART_STATUS_READY_PU);
        }
    }
    public void reject(){
        if (isPending()){
            //only pending orders can be rejected by employee
            cartItemStatus = CART_STATUS_REJECTED;
        }  else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " +
                    cartItemStatus+ " to " + CART_STATUS_REJECTED);
        }
    }
    public void cancel(){
        if (isPending()){
            //only pending orders can be cancelled by customer
            cartItemStatus = CART_STATUS_CANCELLED;
        }  else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " +
                    cartItemStatus+ " to " + CART_STATUS_CANCELLED);
        }
    }
    public void completeOrder(){
        if (isReadyPu()){
            //orders can only be completed if ready for pick-up
            cartItemStatus = CART_STATUS_COMPLETED;
        }  else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " +
                    cartItemStatus+ " to " + CART_STATUS_COMPLETED);
        }
    }
    private void validateCartStatus (){
        if (!(CART_STATUS_PENDING.equals(cartItemStatus)
                ||CART_STATUS_REJECTED.equals(cartItemStatus)
                ||CART_STATUS_READY_PU.equals(cartItemStatus)
                ||CART_STATUS_CANCELLED.equals(cartItemStatus)
                ||CART_STATUS_COMPLETED.equals(cartItemStatus))){
            throw new IllegalArgumentException(cartItemStatus + " is not a valid order status");
        }
    }

    //LAST MINUTE ADD
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
