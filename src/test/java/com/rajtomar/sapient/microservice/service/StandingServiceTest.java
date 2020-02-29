package com.rajtomar.sapient.microservice.service;

import com.rajtomar.sapient.microservice.exception.NoCountryFoundException;
import com.rajtomar.sapient.microservice.exception.NoLeagueFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StandingServiceTest {

    @Mock
    CountryService countryService;

    @Mock
    LeagueService leagueService;

    @MockBean
    StandingService standingService;

    @Test
    public void getCountryIdSuccessTest() throws NoCountryFoundException, NoLeagueFoundException {
        when(countryService.getCountryId("England")).thenReturn("41");
        when(leagueService.getLeagueId("41", "Championship")).thenReturn("149");
        try {
            standingService.getStanding(leagueService.getLeagueId(countryService.getCountryId("England"), "Championship"), "West Brom");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

}
