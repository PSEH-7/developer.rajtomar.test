package com.rajtomar.sapient.microservice;

import com.rajtomar.sapient.microservice.config.ExternalAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MicroserviceApplicationTests {

    @Autowired
    private ExternalAPI externalApi;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertNotNull(externalApi);
        assertNotNull(externalApi.getUrlWithApiKey());
        assertNotEquals("", externalApi.getUrlWithApiKey());
        assertNotNull(restTemplate);
    }

}
