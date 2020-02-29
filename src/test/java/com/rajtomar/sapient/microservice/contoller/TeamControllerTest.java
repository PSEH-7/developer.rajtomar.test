package com.rajtomar.sapient.microservice.contoller;

import com.rajtomar.sapient.microservice.response.Standing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.rajtomar.sapient.microservice.Constants.TEAM_SCORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void getScoreTest() {
        String url = TEAM_SCORE + "?countryName=England&leagueName=Championship&teamName=West Brom";
        ResponseEntity<Standing> responseEntity = restTemplate.getForEntity(url, Standing.class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        Standing standing = responseEntity.getBody();
        assertNotNull(standing);
        assertEquals("England", standing.getCountryName());
        assertEquals("41", standing.getCountryId());
        assertEquals("149", standing.getLeagueId());
        assertEquals("Championship", standing.getLeagueName());
        assertEquals("2625", standing.getTeamId());
        assertEquals("West Brom", standing.getTeamName());
        assertEquals("1", standing.getPosition());
    }

}
