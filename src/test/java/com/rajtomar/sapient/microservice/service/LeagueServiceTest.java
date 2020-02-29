package com.rajtomar.sapient.microservice.service;

import com.rajtomar.sapient.microservice.exception.NoCountryFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeagueServiceTest {

    @Mock
    CountryService countryService;

    @MockBean
    LeagueService leagueService;

    @Test
    public void getLeagueIdSuccessTest() throws NoCountryFoundException {
        when(countryService.getCountryId("England")).thenReturn("41");
        try {
            leagueService.getLeagueId(countryService.getCountryId("England"), "Championship");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

}
