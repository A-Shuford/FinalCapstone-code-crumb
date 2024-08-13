package com.techelevator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegisterUserDtoTest {

    @Test
    void testGetAndSetUsername() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        String expectedUsername = "testUser";

        // Act
        registerUserDto.setUsername(expectedUsername);
        String actualUsername = registerUserDto.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testGetAndSetYourName() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        String expectedYourName = "John Doe";

        // Act
        registerUserDto.setYourName(expectedYourName);
        String actualYourName = registerUserDto.getYourName();

        // Assert
        assertEquals(expectedYourName, actualYourName);
    }

    @Test
    void testGetAndSetEmail() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        String expectedEmail = "test@example.com";

        // Act
        registerUserDto.setEmail(expectedEmail);
        String actualEmail = registerUserDto.getEmail();

        // Assert
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testGetAndSetPhoneNumber() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        String expectedPhoneNumber = "123-456-7890";

        // Act
        registerUserDto.setPhoneNumber(expectedPhoneNumber);
        String actualPhoneNumber = registerUserDto.getPhoneNumber();

        // Assert
        assertEquals(expectedPhoneNumber, actualPhoneNumber);
    }

    @Test
    void testGetAndSetPassword() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        String expectedPassword = "securePassword";

        // Act
        registerUserDto.setPassword(expectedPassword);
        String actualPassword = registerUserDto.getPassword();

        // Assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testGetAndSetConfirmPassword() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        String expectedConfirmPassword = "securePassword";

        // Act
        registerUserDto.setConfirmPassword(expectedConfirmPassword);
        String actualConfirmPassword = registerUserDto.getConfirmPassword();

        // Assert
        assertEquals(expectedConfirmPassword, actualConfirmPassword);
    }

    @Test
    void testGetAndSetRole() {
        // Arrange
        RegisterUserDto registerUserDto = new RegisterUserDto();
        String expectedRole = "USER";

        // Act
        registerUserDto.setRole(expectedRole);
        String actualRole = registerUserDto.getRole();

        // Assert
        assertEquals(expectedRole, actualRole);
    }
}
