package com.app.wrapper.util;

import com.app.wrapper.model.GeneralSheetDto;
import com.app.wrapper.service.WindSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class Utils {

    private final WindSheetService windSheetService;

    @Value("${sheets.wind.sheet-name}")
    String windSheetName;

    @Value("${sheets.snow1.sheet-name}")
    private String firstSnowSheetName;

    @Value("${sheets.snow2.sheet-name}")
    private String secondSnowSheetName;

    @Autowired
    public Utils(WindSheetService windSheetService) {
        this.windSheetService = windSheetService;
    }


    // Метод для получения Id листа и range для расчетных данных
    public HashMap <String, String> getSheetData(boolean inputDataNeed, String sheetName) {
        if (windSheetName.equals(sheetName)) {
            return windSheetService.getData(inputDataNeed);
        } else if (firstSnowSheetName.equals(sheetName)) {
            // TODO: написать утилиту для снега 1
        } else if (secondSnowSheetName.equals(sheetName)) {
            // TODO: написать утилиту для снега 2
        }

        return null;
    }


    // Метод для преобразования ответа (колонок для вводных данных) от googleApi в HashMap
    public ResponseEntity<HashMap<String, Float>> convertCellsContentToMap(ResponseEntity<GeneralSheetDto> inputCells, String sheetName, boolean inputCellsNeeded) {
        if (Objects.requireNonNull(inputCells.getBody()).getValues() != null) {
            Object [][] values = inputCells.getBody().getValues();

            if (windSheetName.equals(sheetName)) {
                return windSheetService.convertCellsToWindParams(values, inputCellsNeeded);
            } else if (firstSnowSheetName.equals(sheetName)) {
                // TODO: написать утилиту для снега 1
            } else if (secondSnowSheetName.equals(sheetName)) {
                // TODO: написать утилиту для снега 2
            }

            throw new IllegalArgumentException("sheetName: is not correct");

        }
        throw new IllegalArgumentException("something went wrong");
    }









}
