package com.app.wrapper.model.outh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenStorage {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonIgnore
    private LocalDateTime expiredAt;


    public boolean isExpired() {
        return expiredAt != null && expiredAt.isBefore(LocalDateTime.now());
    }

    public void calculateExpiredAt() {
        this.expiredAt = LocalDateTime.now().plusSeconds(expiresIn);
    }


    public String getAccessToken() {
        return accessToken;
    }


}
