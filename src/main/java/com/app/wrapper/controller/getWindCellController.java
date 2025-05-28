package com.app.wrapper.controller;

import com.app.wrapper.service.GoogleApiService;
import com.app.wrapper.util.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/spreadsheets/wind/")
public class getWindCellController {

    private final GoogleApiService googleApiService;

    @Value("${sheets.wind.sheet-name}")
    String windSheetName;


    public getWindCellController(GoogleApiService googleApiService, Utils util) {
        this.googleApiService = googleApiService;
    }


    @GetMapping(value = "input")
    public ResponseEntity<HashMap<String, Float>> getInput() {
        return googleApiService.getInputCells(windSheetName);
    }

    @GetMapping(value = "result")
    public ResponseEntity<HashMap<String, Float>> getResult() {
        return googleApiService.getResultCells(windSheetName);
    }

}
