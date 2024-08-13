package com.techelevator.model;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGetAndSetId() {
        // Arrange
        User user = new User();
        int expectedId = 101;

        // Act
        user.setId(expectedId);
        int actualId = user.getId();

        // Assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetAndSetUsername() {
        // Arrange
        User user = new User();
        String expectedUsername = "johndoe";

        // Act
        user.setUsername(expectedUsername);
        String actualUsername = user.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testGetAndSetYourName() {
        // Arrange
        User user = new User();
        String expectedName = "John Doe";

        // Act
        user.setYourName(expectedName);
        String actualName = user.getYourName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    void testGetAndSetEmail() {
        // Arrange
        User user = new User();
        String expectedEmail = "john.doe@example.com";

        // Act
        user.setEmail(expectedEmail);
        String actualEmail = user.getEmail();

        // Assert
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testGetAndSetPhoneNumber() {
        // Arrange
        User user = new User();
        String expectedPhoneNumber = "123-456-7890";

        // Act
        user.setPhoneNumber(expectedPhoneNumber);
        String actualPhoneNumber = user.getPhoneNumber();

        // Assert
        assertEquals(expectedPhoneNumber, actualPhoneNumber);
    }

    @Test
    void testGetAndSetPassword() {
        // Arrange
        User user = new User();
        String expectedPassword = "password123";

        // Act
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        // Assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testGetAndSetRole() {
        // Arrange
        User user = new User();
        String expectedRole = "ADMIN";

        // Act
        user.setRole(expectedRole);
        String actualRole = user.getRole();

        // Assert
        assertEquals(expectedRole, actualRole);
    }

    @Test
    void testIsAndSetActivated() {
        // Arrange
        User user = new User();
        boolean expectedActivated = true;

        // Act
        user.setActivated(expectedActivated);
        boolean actualActivated = user.isActivated();

        // Assert
        assertEquals(expectedActivated, actualActivated);
    }

    @Test
    void testGetAndSetAuthorities() {
        // Arrange
        User user = new User();
        Set<Authority> expectedAuthorities = new HashSet<>();
        expectedAuthorities.add(new Authority("ROLE_USER"));

        // Act
        user.setAuthorities(expectedAuthorities);
        Set<Authority> actualAuthorities = user.getAuthorities();

        // Assert
        assertEquals(expectedAuthorities, actualAuthorities);
    }

    @Test
    void testSetAuthoritiesFromString() {
        // Arrange
        User user = new User();
        String authoritiesString = "USER,ADMIN";
        Set<Authority> expectedAuthorities = new HashSet<>();
        expectedAuthorities.add(new Authority("ROLE_USER"));
        expectedAuthorities.add(new Authority("ROLE_ADMIN"));

        // Act
        user.setAuthorities(authoritiesString);
        Set<Authority> actualAuthorities = user.getAuthorities();

        // Assert
        assertEquals(expectedAuthorities, actualAuthorities);
    }


}
