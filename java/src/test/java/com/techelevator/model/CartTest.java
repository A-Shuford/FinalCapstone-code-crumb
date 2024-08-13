package com.techelevator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

    private Cart cart;
    private List<CartItem> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        cart = new Cart(items);
    }

    @Test
    void testGetAndSetTax() {
        // Arrange
        BigDecimal expectedTax = new BigDecimal("5.00");

        // Act
        cart.setTax(expectedTax);
        BigDecimal actualTax = cart.getTax();

        // Assert
        assertEquals(expectedTax, actualTax);
    }

   
    @Test
    void testGetTotalWithNoItems() {
        // Arrange
        BigDecimal expectedTotal = new BigDecimal("0.00");
        cart.setTax(new BigDecimal("0.00"));

        // Act
        BigDecimal actualTotal = cart.getTotal();

        // Assert
        assertEquals(expectedTotal, actualTotal);
    }
}
