package com.app.wrapper.service;

import com.app.wrapper.model.GeneralSheetDto;
import com.app.wrapper.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class GoogleApiService {

    private final RestTemplate rest;
    private final Utils util;

    @Autowired
    public GoogleApiService(RestTemplate rest, Utils util) {
        this.rest = rest;
        this.util = util;
    }

    @Value("${spring.google-api.get-cell-url}")
    private String baseUrl;

    @Value("${api.key}")
    private String apiKey;



    public ResponseEntity<HashMap <String, Float>> getCellsContent(String sheetName, boolean inputCellsNeeded) {
        HashMap<String, String> sheetData = util.getSheetData(inputCellsNeeded, sheetName);
        String sheetId = sheetData.get("id");
        String range = sheetData.get("range");

        ResponseEntity<GeneralSheetDto> cellsContent = getCellsByRange(sheetId, range);
        return util.convertCellsContentToMap(cellsContent, sheetName, inputCellsNeeded);
    }




    private ResponseEntity<GeneralSheetDto> getCellsByRange(String sheetId, String range) {

        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("key", apiKey)
                .buildAndExpand(sheetId, range)
                .toUriString();

        return rest.getForEntity(url, GeneralSheetDto.class);
    }



}
