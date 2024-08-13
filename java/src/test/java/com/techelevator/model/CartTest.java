package com.techelevator.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void testDefaultConstructor() {
        // Arrange
        Cart cart = new Cart();

        // Act
        BigDecimal tax = cart.getTax();
        CartItem[] items = cart.getItems();

        // Assert
        assertEquals(new BigDecimal("0.00"), tax);
        assertEquals(0, items.length);
    }

    @Test
    void testConstructorWithItems() {
        // Arrange
        Cake cake = new Cake(1, "Vanilla Cake", "Style", "Medium", "Vanilla", "Cream", "Buttercream", "Birthday", true, "Happy Birthday", 1, new BigDecimal("20.00"), "vanilla.jpg");
        CartItem item1 = new CartItem(cake, 2);
        List<CartItem> itemList = Arrays.asList(item1);
        Cart cart = new Cart(itemList);

        // Act
        CartItem[] items = cart.getItems();

        // Assert
        assertEquals(1, items.length);
        assertEquals(item1, items[0]);
    }

    @Test
    void testGetTax() {
        // Arrange
        Cart cart = new Cart();
        BigDecimal expectedTax = new BigDecimal("5.00");

        // Act
        cart.setTax(expectedTax);
        BigDecimal actualTax = cart.getTax();

        // Assert
        assertEquals(expectedTax, actualTax);
    }

    @Test
    void testSetTax() {
        // Arrange
        Cart cart = new Cart();
        BigDecimal expectedTax = new BigDecimal("7.50");

        // Act
        cart.setTax(expectedTax);
        BigDecimal actualTax = cart.getTax();

        // Assert
        assertEquals(expectedTax, actualTax);
    }

    @Test
    void testGetItemSubtotal_NoItems() {
        // Arrange
        Cart cart = new Cart();

        // Act
        BigDecimal subtotal = cart.getItemSubtotal();

        // Assert
        assertEquals(new BigDecimal("0.00"), subtotal);
    }

    @Test
    void testGetItemSubtotal_WithItems() {
        // Arrange
        Cake cake1 = new Cake(1, "Chocolate Cake", "Style", "Medium", "Chocolate", "Cream", "Buttercream", "Birthday", true, "Happy Birthday", 1, new BigDecimal("15.00"), "chocolate.jpg");
        CartItem item1 = new CartItem(cake1, 2);

        Cake cake2 = new Cake(2, "Vanilla Cake", "Style", "Small", "Vanilla", "Cream", "Buttercream", "Birthday", true, "Congrats", 1, new BigDecimal("10.00"), "vanilla.jpg");
        CartItem item2 = new CartItem(cake2, 3);

        List<CartItem> itemList = Arrays.asList(item1, item2);
        Cart cart = new Cart(itemList);

        // Act
        BigDecimal subtotal = cart.getItemSubtotal();

        // Assert
        assertEquals(new BigDecimal("60.00"), subtotal);
    }

    @Test
    void testGetTotal_NoItems() {
        // Arrange
        Cart cart = new Cart();
        cart.setTax(new BigDecimal("5.00"));

        // Act
        BigDecimal total = cart.getTotal();

        // Assert
        assertEquals(new BigDecimal("5.00"), total);
    }

    @Test
    void testGetTotal_WithItems() {
        // Arrange
        Cake cake1 = new Cake(1, "Chocolate Cake", "Style", "Medium", "Chocolate", "Cream", "Buttercream", "Birthday", true, "Happy Birthday", 1, new BigDecimal("15.00"), "chocolate.jpg");
        CartItem item1 = new CartItem(cake1, 2);

        Cake cake2 = new Cake(2, "Vanilla Cake", "Style", "Small", "Vanilla", "Cream", "Buttercream", "Birthday", true, "Congrats", 1, new BigDecimal("10.00"), "vanilla.jpg");
        CartItem item2 = new CartItem(cake2, 3);

        List<CartItem> itemList = Arrays.asList(item1, item2);
        Cart cart = new Cart(itemList);
        cart.setTax(new BigDecimal("5.00"));

        // Act
        BigDecimal total = cart.getTotal();

        // Assert
        assertEquals(new BigDecimal("65.00"), total);
    }
}
