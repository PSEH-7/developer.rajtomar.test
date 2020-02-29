package com.rajtomar.sapient.microservice.service;

import com.rajtomar.sapient.microservice.config.ExternalAPI;
import com.rajtomar.sapient.microservice.exception.NoLeagueFoundException;
import com.rajtomar.sapient.microservice.response.League;
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
public class LeagueService {

    @Autowired
    ExternalAPI externalAPI;

    @Autowired
    RestTemplate restTemplate;

    public String getLeagueId(String countryId, String leagueName) throws NoLeagueFoundException {
        String url = externalAPI.getUrlWithApiKey() + "&action=get_leagues&country_id=" + countryId;
        ResponseEntity<List<League>> leagueResponseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<League>>() {
        });
        League foundLeague = null;
        if (leagueResponseEntity.getStatusCode().is2xxSuccessful() && Objects.nonNull(leagueResponseEntity.getBody())) {
            foundLeague = leagueResponseEntity.getBody()
                    .stream()
                    .filter(league -> league.getLeagueName().equals(leagueName))
                    .findFirst()
                    .orElse(null);
        }
        if (foundLeague != null) {
            return foundLeague.getLeagueId();
        } else {
            throw new NoLeagueFoundException("No League found with this name");
        }
    }

}
