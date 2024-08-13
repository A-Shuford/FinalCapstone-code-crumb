package com.techelevator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginResponseDtoTest {

    @Test
    void testGetToken() {
        // Arrange
        String expectedToken = "sampleToken";
        User sampleUser = new User(); // Assuming User is a valid class with a default constructor
        LoginResponseDto loginResponseDto = new LoginResponseDto(expectedToken, sampleUser);

        // Act
        String actualToken = loginResponseDto.getToken();

        // Assert
        assertEquals(expectedToken, actualToken);
    }

    @Test
    void testSetToken() {
        // Arrange
        String expectedToken = "newToken";
        User sampleUser = new User(); // Assuming User is a valid class with a default constructor
        LoginResponseDto loginResponseDto = new LoginResponseDto("sampleToken", sampleUser);

        // Act
        loginResponseDto.setToken(expectedToken);
        String actualToken = loginResponseDto.getToken();

        // Assert
        assertEquals(expectedToken, actualToken);
    }

    @Test
    void testGetUser() {
        // Arrange
        User expectedUser = new User(); // Assuming User is a valid class with a default constructor
        LoginResponseDto loginResponseDto = new LoginResponseDto("sampleToken", expectedUser);

        // Act
        User actualUser = loginResponseDto.getUser();

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testSetUser() {
        // Arrange
        User expectedUser = new User(); // Assuming User is a valid class with a default constructor
        LoginResponseDto loginResponseDto = new LoginResponseDto("sampleToken", new User());

        // Act
        loginResponseDto.setUser(expectedUser);
        User actualUser = loginResponseDto.getUser();

        // Assert
        assertEquals(expectedUser, actualUser);
    }
}
