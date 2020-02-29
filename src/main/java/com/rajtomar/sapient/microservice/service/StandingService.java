package com.rajtomar.sapient.microservice.service;

import com.rajtomar.sapient.microservice.config.ExternalAPI;
import com.rajtomar.sapient.microservice.response.Standing;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StandingService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExternalAPI externalApi;

    public Standing getStanding(String leagueId, String teamName) {
        String url = externalApi.getUrlWithApiKey() + "&action=get_standings&league_id=" + leagueId;
        ResponseEntity<List<Standing>> standingResponseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Standing>>() {
        });
        Standing foundStanding = null;
        if (standingResponseEntity.getStatusCode().is2xxSuccessful() && Objects.nonNull(standingResponseEntity.getBody())) {
            foundStanding = standingResponseEntity.getBody()
                    .stream()
                    .filter(standing -> standing.getTeamName().equals(teamName))
                    .findFirst()
                    .orElse(null);
        }
        if (foundStanding != null) {
            return foundStanding;
        } else {
            throw new IllegalArgumentException("No Standing found with this name");
        }
    }

}
