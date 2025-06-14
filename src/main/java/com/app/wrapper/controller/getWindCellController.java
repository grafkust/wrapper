package com.app.wrapper.controller;

import com.app.wrapper.model.GoogleSheetsUpdateRequestDto;
import com.app.wrapper.model.GoogleSheetsUpdateResponseDto;
import com.app.wrapper.service.GoogleApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/spreadsheets/wind/")
public class getWindCellController {

    private final GoogleApiService googleApiService;

    @Value("${sheets.wind.sheet-name}")
    String windSheetName;

    public getWindCellController(GoogleApiService googleApiService) {
        this.googleApiService = googleApiService;
    }


    @GetMapping(value = "input")
    public ResponseEntity<HashMap<String, Float>> getInput() {
        return googleApiService.getCellsContent(windSheetName, true);
    }

    @GetMapping(value = "result")
    public ResponseEntity<HashMap<String, Float>> getResult() {
        return googleApiService.getCellsContent(windSheetName, false);
    }

    @PutMapping(value = "update")
    public ResponseEntity<GoogleSheetsUpdateResponseDto> update(@RequestBody GoogleSheetsUpdateRequestDto inputData) {
        return googleApiService.updateCellsContent(windSheetName, inputData, true);
    }

}
