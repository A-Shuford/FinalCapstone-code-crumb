package com.techelevator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem();
        cartItem.setCartItemStatus(CartItem.CART_STATUS_PENDING);
    }

    @Test
    void testInitialStatusIsPending() {
        assertTrue(cartItem.isPending());
        assertFalse(cartItem.isRejected());
        assertFalse(cartItem.isCancelled());
        assertFalse(cartItem.isReadyPu());
        assertFalse(cartItem.isCompleted());
    }

    @Test
    void testConfirmPickupFromPending() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_PENDING);

        // Act
        cartItem.confirmPickup();

        // Assert
        assertTrue(cartItem.isReadyPu());
    }

    @Test
    void testConfirmPickupFromNonPendingThrowsException() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_CANCELLED);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, cartItem::confirmPickup);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Cannot change order status from Cancelled by customer to Ready for pick-up", exception.getReason());
    }

    @Test
    void testRejectFromPending() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_PENDING);

        // Act
        cartItem.reject();

        // Assert
        assertTrue(cartItem.isRejected());
    }

    @Test
    void testRejectFromNonPendingThrowsException() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_READY_PU);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, cartItem::reject);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Cannot change order status from Ready for pick-up to Rejected by bakery", exception.getReason());
    }

    @Test
    void testCancelFromPending() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_PENDING);

        // Act
        cartItem.cancel();

        // Assert
        assertTrue(cartItem.isCancelled());
    }

    @Test
    void testCancelFromNonPendingThrowsException() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_COMPLETED);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, cartItem::cancel);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Cannot change order status from Order Completed to Cancelled by customer", exception.getReason());
    }

    @Test
    void testCompleteOrderFromReadyPu() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_READY_PU);

        // Act
        cartItem.completeOrder();

        // Assert
        assertTrue(cartItem.isCompleted());
    }

    @Test
    void testCompleteOrderFromNonReadyPuThrowsException() {
        // Arrange
        cartItem.setCartItemStatus(CartItem.CART_STATUS_REJECTED);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, cartItem::completeOrder);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Cannot change order status from Rejected by bakery to Order Completed", exception.getReason());
    }

    @Test
    void testInvalidCartItemStatusThrowsException() {
        // Arrange
        String invalidStatus = "Invalid Status";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItem.setCartItemStatus(invalidStatus));
        assertEquals(invalidStatus + " is not a valid order status", exception.getMessage());
    }
}


