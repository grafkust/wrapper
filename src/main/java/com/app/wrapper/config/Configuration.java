package com.app.wrapper.config;

import com.app.wrapper.model.outh.ServiceAccountCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

@org.springframework.context.annotation.Configuration
public class Configuration {


    @Value("${service-account.file-name}")
    private String credentialsFileName;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ServiceAccountCredentials serviceAccountCredentials() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(credentialsFileName);

        return mapper.readValue(inputStream, ServiceAccountCredentials.class);
    }

}
