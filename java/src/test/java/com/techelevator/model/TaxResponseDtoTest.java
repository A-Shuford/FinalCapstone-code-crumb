package com.techelevator.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TaxResponseDtoTest {

    @Test
    void testGetAndSetSalesTax() {
        // Arrange
        TaxResponseDto taxResponseDto = new TaxResponseDto();
        BigDecimal expectedSalesTax = new BigDecimal("7.25");

        // Act
        taxResponseDto.setSalesTax(expectedSalesTax);
        BigDecimal actualSalesTax = taxResponseDto.getSalesTax();

        // Assert
        assertEquals(expectedSalesTax, actualSalesTax);
    }

    @Test
    void testGetAndSetLastUpdated() {
        // Arrange
        TaxResponseDto taxResponseDto = new TaxResponseDto();
        String expectedLastUpdated = "2024-08-13";

        // Act
        taxResponseDto.setLastUpdated(expectedLastUpdated);
        String actualLastUpdated = taxResponseDto.getLastUpdated();

        // Assert
        assertEquals(expectedLastUpdated, actualLastUpdated);
    }
}
