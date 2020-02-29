package com.rajtomar.sapient.microservice.service;

import com.rajtomar.sapient.microservice.config.ExternalAPI;
import com.rajtomar.sapient.microservice.exception.NoCountryFoundException;
import com.rajtomar.sapient.microservice.response.Country;
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
public class CountryService {

    @Autowired
    ExternalAPI externalAPI;

    @Autowired
    RestTemplate restTemplate;

    public String getCountryId(String countryName) throws NoCountryFoundException {
        String url = externalAPI.getUrlWithApiKey() + "&action=get_countries";
        ResponseEntity<List<Country>> countryResponseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {
        });
        Country foundCountry = null;
        if (countryResponseEntity.getStatusCode().is2xxSuccessful() && Objects.nonNull(countryResponseEntity.getBody())) {
            foundCountry = countryResponseEntity.getBody()
                    .stream()
                    .filter(country -> country.getCountryName().equals(countryName))
                    .findFirst()
                    .orElse(null);
        }
        if (foundCountry != null) {
            return foundCountry.getCountryId();
        } else {
            throw new NoCountryFoundException("No country found with this name");
        }
    }
}
