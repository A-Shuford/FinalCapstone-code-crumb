package com.techelevator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginDtoTest {

    @Test
    void testGetAndSetUsername() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        String expectedUsername = "testUser";

        // Act
        loginDto.setUsername(expectedUsername);
        String actualUsername = loginDto.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testGetAndSetPassword() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        String expectedPassword = "testPassword";

        // Act
        loginDto.setPassword(expectedPassword);
        String actualPassword = loginDto.getPassword();

        // Assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testToString() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testUser");
        loginDto.setPassword("testPassword");
        String expectedString = "LoginDTO{username='testUser', password='testPassword'}";

        // Act
        String actualString = loginDto.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }
}
