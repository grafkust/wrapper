package com.app.wrapper.model.outh;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceAccountCredentials {

    @JsonProperty("client_email")
    private String clientEmail;

    @JsonProperty("private_key")
    private String privateKey;

}
