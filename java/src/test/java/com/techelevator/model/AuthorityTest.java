package com.techelevator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthorityTest {

    @Test
    void testGetName() {
        // Arrange
        String expectedName = "ROLE_USER";
        Authority authority = new Authority(expectedName);

        // Act
        String actualName = authority.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    void testSetName() {
        // Arrange
        Authority authority = new Authority("ROLE_USER");
        String expectedName = "ROLE_ADMIN";

        // Act
        authority.setName(expectedName);

        // Assert
        assertEquals(expectedName, authority.getName());
    }

    @Test
    void testEquals_SameObject() {
        // Arrange
        Authority authority = new Authority("ROLE_USER");

        // Act & Assert
        assertTrue(authority.equals(authority));
    }

    @Test
    void testEquals_DifferentObjectSameName() {
        // Arrange
        Authority authority1 = new Authority("ROLE_USER");
        Authority authority2 = new Authority("ROLE_USER");

        // Act & Assert
        assertTrue(authority1.equals(authority2));
    }

    @Test
    void testEquals_DifferentObjectDifferentName() {
        // Arrange
        Authority authority1 = new Authority("ROLE_USER");
        Authority authority2 = new Authority("ROLE_ADMIN");

        // Act & Assert
        assertFalse(authority1.equals(authority2));
    }

    @Test
    void testEquals_NullObject() {
        // Arrange
        Authority authority = new Authority("ROLE_USER");

        // Act & Assert
        assertFalse(authority.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        // Arrange
        Authority authority = new Authority("ROLE_USER");
        String otherObject = "Not an Authority";

        // Act & Assert
        assertFalse(authority.equals(otherObject));
    }

    @Test
    void testHashCode_SameName() {
        // Arrange
        Authority authority1 = new Authority("ROLE_USER");
        Authority authority2 = new Authority("ROLE_USER");

        // Act & Assert
        assertEquals(authority1.hashCode(), authority2.hashCode());
    }

    @Test
    void testHashCode_DifferentName() {
        // Arrange
        Authority authority1 = new Authority("ROLE_USER");
        Authority authority2 = new Authority("ROLE_ADMIN");

        // Act & Assert
        assertNotEquals(authority1.hashCode(), authority2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Authority authority = new Authority("ROLE_USER");
        String expectedString = "Authority{name=ROLE_USER}";

        // Act
        String actualString = authority.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }
}


