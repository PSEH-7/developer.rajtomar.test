package com.rajtomar.sapient.microservice.service;

import com.rajtomar.sapient.microservice.exception.NoCountryFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryServiceTest {

    @Autowired
    CountryService countryService;

    @Test
    public void getCountryIdSuccessTest() {
        try {
            countryService.getCountryId("England");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void getCountryIdExceptionTest() {
        Exception exception = assertThrows(NoCountryFoundException.class, () -> {
            countryService.getCountryId("anything");
        });
        String expectedMessage = "No country found with this name";
        assertEquals(exception.getMessage(), expectedMessage);
    }

}
