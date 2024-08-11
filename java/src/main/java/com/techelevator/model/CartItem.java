package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

public class CartItem {
    private int cartItemId;
    private User user;
    private Cake cake;
    private LocalDate pickupDate;
    private LocalTime pickupTime;
    private int quantity;
    private String cartItemStatus; // this is from the INNER JOIN to get the name, not the id

    public static final String CART_STATUS_PENDING = "Pending";
    public static final String CART_STATUS_REJECTED = "Rejected by bakery";
    public static final String CART_STATUS_CANCELLED = "Cancelled by customer";
    public static final String CART_STATUS_READY_PU = "Ready for pick-up";
    public static final String CART_STATUS_COMPLETED = "Order Completed";

    public CartItem() {
    }

    public CartItem(int cartItemId, String cartItemStatus) {
        this.cartItemId = cartItemId;
        this.cartItemStatus = cartItemStatus;
        validateCartStatus();  // Validate status on creation
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        validateCartStatus();  // Validate status on setting
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPending() {
        return CART_STATUS_PENDING.equals(this.cartItemStatus);
    }

    public boolean isRejected() {
        return CART_STATUS_REJECTED.equals(this.cartItemStatus);
    }

    public boolean isCancelled() {
        return CART_STATUS_CANCELLED.equals(this.cartItemStatus);
    }

    public boolean isReadyPu() {
        return CART_STATUS_READY_PU.equals(this.cartItemStatus);
    }

    public boolean isCompleted() {
        return CART_STATUS_COMPLETED.equals(this.cartItemStatus);
    }

    public void confirmPickup() {
        if (isPending()) {
            cartItemStatus = CART_STATUS_READY_PU;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " + cartItemStatus + " to " + CART_STATUS_READY_PU);
        }
    }

    public void reject() {
        if (isPending()) {
            cartItemStatus = CART_STATUS_REJECTED;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " + cartItemStatus + " to " + CART_STATUS_REJECTED);
        }
    }

    public void cancel() {
        if (isPending()) {
            cartItemStatus = CART_STATUS_CANCELLED;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " + cartItemStatus + " to " + CART_STATUS_CANCELLED);
        }
    }

    public void completeOrder() {
        if (isReadyPu()) {
            cartItemStatus = CART_STATUS_COMPLETED;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot change order status from " + cartItemStatus + " to " + CART_STATUS_COMPLETED);
        }
    }

    private void validateCartStatus() {
        if (!(CART_STATUS_PENDING.equals(cartItemStatus)
                || CART_STATUS_REJECTED.equals(cartItemStatus)
                || CART_STATUS_READY_PU.equals(cartItemStatus)
                || CART_STATUS_CANCELLED.equals(cartItemStatus)
                || CART_STATUS_COMPLETED.equals(cartItemStatus))) {
            throw new IllegalArgumentException(cartItemStatus + " is not a valid order status");
        }
    }
}
