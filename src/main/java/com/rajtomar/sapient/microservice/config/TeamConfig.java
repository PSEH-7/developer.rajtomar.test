package com.rajtomar.sapient.microservice.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "team.ext")
public class TeamConfig implements ExternalAPI {

    @NotBlank
    String url;

    @NotBlank
    String apiKey;

    @Override
    public String getUrlWithApiKey() {
        return url + "APIkey=" + apiKey;
    }

}
