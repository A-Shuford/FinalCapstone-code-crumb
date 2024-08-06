package com.techelevator.model;

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

    @NotEmpty
    private LocalDate pickupDate;
    @NotEmpty
    private LocalTime pickupTime;
    @Positive
    private int quantity;

    public CartItem() {
    }

    public CartItem(int cartItemId, int userId, int cakeId, int quantity) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.cakeId = cakeId;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
