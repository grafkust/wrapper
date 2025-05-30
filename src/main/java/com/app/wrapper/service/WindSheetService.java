package com.app.wrapper.service;

import com.app.wrapper.model.wind.WindInputParams;
import com.app.wrapper.model.wind.WindOutputParams;
import com.app.wrapper.model.wind.WindParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class WindSheetService {

    @Value("${sheets.wind.sheet-id}")
    private String sheetId;
    
    @Value("${sheets.wind.input-range}")
    private String inputRange;
    
    @Value("${sheets.wind.result-range}")
    private String resultRange;


    public HashMap<String, String> getData(Boolean inputDataNeed) {
        HashMap <String, String> result = new HashMap<>();
        result.put("id", sheetId);
        result.put("range", inputDataNeed ? inputRange : resultRange);
        return result;
    }


    public ResponseEntity<HashMap<String, Float>> convertCellsToWindParams(Object[][] cells, boolean isInputData) {
        HashMap<String, Float> windData = new HashMap<>();

        WindParam[] params = isInputData ? WindInputParams.values() : WindOutputParams.values();

        for (WindParam param : params) {
            putData(windData, param, cells);
        }

        return ResponseEntity.ok(windData);
    }



    private void putData(HashMap<String, Float> map, WindParam res, Object[][] resultCells){
        String value = (String) resultCells[res.getRow()][res.getCol()];
        value = value.replace(",", ".");
        map.put(res.getKey(), Float.parseFloat(value));
    }






}
