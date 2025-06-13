package com.app.wrapper.outh;

import com.app.wrapper.model.outh.ServiceAccountCredentials;
import com.app.wrapper.model.outh.TokenStorage;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Service
public class ServiceAccountTokenService {

    private final ServiceAccountCredentials credentials;
    private final RestTemplate rest;

    @Value("${spring.google-api.token-url}")
    private String tokenUrl;

    @Value("${spring.google-api.scope}")
    private String scopeUrl;

    public ServiceAccountTokenService(ServiceAccountCredentials credentials, RestTemplate rest) {
        this.credentials = credentials;
        this.rest = rest;
    }


    public String getAccessToken() {
        String jwt = createJwtToken();
        return exchangeJwtForAccessToken(jwt);
    }

    private String createJwtToken() {
        long now = System.currentTimeMillis() / 1000;

        try {
            return Jwts.builder()
                    .issuer(credentials.getClientEmail())
                    .audience().add(tokenUrl).and()
                    .claim("scope", scopeUrl)
                    .issuedAt(new Date(now * 1000))
                    .expiration(new Date((now + 3600) * 1000))
                    .signWith(getPrivateKey())
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private PrivateKey getPrivateKey() throws Exception {
        String key = credentials.getPrivateKey();

        byte[] decoded = Base64.getDecoder().decode(key);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        return keyFactory.generatePrivate(keySpec);
    }

    private String exchangeJwtForAccessToken(String jwt) {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer");
        body.add("assertion", jwt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        TokenStorage accessToken = rest.postForObject(tokenUrl, request, TokenStorage.class);
        accessToken.calculateExpiredAt();
        return accessToken.getAccessToken();
    }


}
