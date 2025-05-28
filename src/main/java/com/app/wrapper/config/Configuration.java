package com.app.wrapper.config;

import com.app.wrapper.util.Utils;
import com.app.wrapper.util.constants.wind.WindSheetUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Utils utils() {
        return new Utils(windUtil());
    }

    @Bean
    public WindSheetUtil windUtil() {
        return new WindSheetUtil();
    }

}
