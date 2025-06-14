package com.app.wrapper.service;

import com.app.wrapper.model.GeneralSheetDto;
import com.app.wrapper.model.GoogleSheetsUpdateRequestDto;
import com.app.wrapper.model.GoogleSheetsUpdateResponseDto;
import com.app.wrapper.outh.ServiceAccountTokenService;
import com.app.wrapper.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class GoogleApiService {

    private final RestTemplate rest;
    private final Utils util;
    private final ServiceAccountTokenService tokenService;

    @Autowired
    public GoogleApiService(RestTemplate rest, Utils util, ServiceAccountTokenService tokenService) {
        this.rest = rest;
        this.util = util;
        this.tokenService = tokenService;
    }

    @Value("${spring.google-api.get-cell-url}")
    private String getCellUrl;

    @Value("${spring.google-api.update-cell-url}")
    private String updateCellUrl;

    @Value("${api.key}")
    private String apiKey;


    public ResponseEntity<HashMap<String, Float>> getCellsContent(String sheetName, boolean inputCellsNeeded) {
        HashMap<String, String> sheetData = util.getSheetData(inputCellsNeeded, sheetName);
        String sheetId = sheetData.get("id");
        String range = sheetData.get("range");

        ResponseEntity<GeneralSheetDto> cellsContent = getCellsByRange(sheetId, range);
        return util.convertCellsContentToMap(cellsContent, sheetName, inputCellsNeeded);
    }


    private ResponseEntity<GeneralSheetDto> getCellsByRange(String sheetId, String range) {
        String url = generateUrl(getCellUrl, true, sheetId, range);
        return rest.getForEntity(url, GeneralSheetDto.class);
    }


    public ResponseEntity<GoogleSheetsUpdateResponseDto> updateCellsContent(String sheetName, GoogleSheetsUpdateRequestDto inputData, boolean isInputCells) {
        HashMap<String, String> sheetData = util.getSheetData(isInputCells, sheetName);
        String sheetId = sheetData.get("id");
        String range = sheetData.get("range");
        return updateCellsContent(sheetId, range, inputData);
    }

    private ResponseEntity<GoogleSheetsUpdateResponseDto> updateCellsContent(String sheetId, String range, GoogleSheetsUpdateRequestDto inputData) {
        String url = generateUrl(updateCellUrl, false, sheetId, range);
        String accessToken = tokenService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GoogleSheetsUpdateRequestDto> request = new HttpEntity<>(inputData, headers);
        return rest.exchange(url, HttpMethod.PUT, request, GoogleSheetsUpdateResponseDto.class);
    }


    private String generateUrl(String baseUrl, boolean isKeyNeeded, String sheetId, String range) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);

        if (isKeyNeeded) {
            builder.queryParam("key", apiKey);
        } else {
            builder.queryParam("valueInputOption", "RAW");
        }

        return builder.buildAndExpand(sheetId, range).toUriString();
    }

}
