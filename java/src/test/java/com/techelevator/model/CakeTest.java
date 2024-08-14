package com.techelevator.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CakeTest {

    @Test
    void testGetAndSetCakeId() {
        // Arrange
        Cake cake = new Cake();
        int expectedId = 1;

        // Act
        cake.setCakeId(expectedId);

        // Assert
        assertEquals(expectedId, cake.getCakeId());
    }

    @Test
    void testGetAndSetCakeName() {
        // Arrange
        Cake cake = new Cake();
        String expectedName = "Chocolate Cake";

        // Act
        cake.setCakeName(expectedName);

        // Assert
        assertEquals(expectedName, cake.getCakeName());
    }

    @Test
    void testGetAndSetCakeStyle() {
        // Arrange
        Cake cake = new Cake();
        String expectedStyle = "Layered";

        // Act
        cake.setCakeStyle(expectedStyle);

        // Assert
        assertEquals(expectedStyle, cake.getCakeStyle());
    }

    @Test
    void testGetAndSetCakeSize() {
        // Arrange
        Cake cake = new Cake();
        String expectedSize = "Medium";

        // Act
        cake.setCakeSize(expectedSize);

        // Assert
        assertEquals(expectedSize, cake.getCakeSize());
    }

    @Test
    void testGetAndSetCakeFlavor() {
        // Arrange
        Cake cake = new Cake();
        String expectedFlavor = "Vanilla";

        // Act
        cake.setCakeFlavor(expectedFlavor);

        // Assert
        assertEquals(expectedFlavor, cake.getCakeFlavor());
    }

    @Test
    void testGetAndSetCakeFilling() {
        // Arrange
        Cake cake = new Cake();
        String expectedFilling = "Cream";

        // Act
        cake.setCakeFilling(expectedFilling);

        // Assert
        assertEquals(expectedFilling, cake.getCakeFilling());
    }

    @Test
    void testGetAndSetCakeFrosting() {
        // Arrange
        Cake cake = new Cake();
        String expectedFrosting = "Chocolate Ganache";

        // Act
        cake.setCakeFrosting(expectedFrosting);

        // Assert
        assertEquals(expectedFrosting, cake.getCakeFrosting());
    }

    @Test
    void testGetAndSetCakeType() {
        // Arrange
        Cake cake = new Cake();
        String expectedType = "Birthday";

        // Act
        cake.setCakeType(expectedType);

        // Assert
        assertEquals(expectedType, cake.getCakeType());
    }

    @Test
    void testHasAndSetHasWriting() {
        // Arrange
        Cake cake = new Cake();
        boolean expectedHasWriting = true;

        // Act
        cake.setHasWriting(expectedHasWriting);

        // Assert
        assertTrue(cake.hasWriting());
    }

    @Test
    void testGetAndSetCustomText() {
        // Arrange
        Cake cake = new Cake();
        String expectedText = "Happy Birthday!";

        // Act
        cake.setCustomText(expectedText);

        // Assert
        assertEquals(expectedText, cake.getCustomText());
    }

    @Test
    void testGetAndSetAmountAvailable() {
        // Arrange
        Cake cake = new Cake();
        int expectedAmount = 10;

        // Act
        cake.setAmountAvailable(expectedAmount);

        // Assert
        assertEquals(expectedAmount, cake.getAmountAvailable());
    }

    @Test
    void testGetAndSetPrice() {
        // Arrange
        Cake cake = new Cake();
        BigDecimal expectedPrice = new BigDecimal("19.99");

        // Act
        cake.setPrice(expectedPrice);

        // Assert
        assertEquals(expectedPrice, cake.getPrice());
    }

    @Test
    void testGetAndSetImageName() {
        // Arrange
        Cake cake = new Cake();
        String expectedImageName = "cake_image.jpg";

        // Act
        cake.setImageName(expectedImageName);

        // Assert
        assertEquals(expectedImageName, cake.getImageName());
    }

    @Test
    void testFullConstructor() {
        // Arrange
        int expectedCakeId = 1;
        String expectedCakeName = "Chocolate Cake";
        String expectedCakeStyle = "Layered";
        String expectedCakeSize = "Medium";
        String expectedCakeFlavor = "Vanilla";
        String expectedCakeFilling = "Cream";
        String expectedCakeFrosting = "Chocolate Ganache";
        String expectedCakeType = "Birthday";
        boolean expectedHasWriting = true;
        String expectedCustomText = "Happy Birthday!";
        int expectedAmountAvailable = 10;
        BigDecimal expectedPrice = new BigDecimal("19.99");
        String expectedImageName = "cake_image.jpg";

        // Act
        Cake cake = new Cake(
                expectedCakeId,
                expectedCakeName,
                expectedCakeStyle,
                expectedCakeSize,
                expectedCakeFlavor,
                expectedCakeFilling,
                expectedCakeFrosting,
                expectedCakeType,
                expectedHasWriting,
                expectedCustomText,
                expectedAmountAvailable,
                expectedPrice,
                expectedImageName
        );

        // Assert
        assertEquals(expectedCakeId, cake.getCakeId());
        assertEquals(expectedCakeName, cake.getCakeName());
        assertEquals(expectedCakeStyle, cake.getCakeStyle());
        assertEquals(expectedCakeSize, cake.getCakeSize());
        assertEquals(expectedCakeFlavor, cake.getCakeFlavor());
        assertEquals(expectedCakeFilling, cake.getCakeFilling());
        assertEquals(expectedCakeFrosting, cake.getCakeFrosting());
        assertEquals(expectedCakeType, cake.getCakeType());
        assertTrue(cake.hasWriting());
        assertEquals(expectedCustomText, cake.getCustomText());
        assertEquals(expectedAmountAvailable, cake.getAmountAvailable());
        assertEquals(expectedPrice, cake.getPrice());
        assertEquals(expectedImageName, cake.getImageName());
    }
}
