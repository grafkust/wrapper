package com.app.wrapper.util;

import com.app.wrapper.model.GeneralSheetDto;
import com.app.wrapper.util.constants.wind.WindSheetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Objects;


public class Utils {

    private final WindSheetUtil windSheetUtil;

    @Value("${sheets.wind.sheet-name}")
    String windSheetName;


    @Value("${sheets.snow1.sheet-name}")
    private String firstSnowSheetName;


    @Value("${sheets.snow2.sheet-name}")
    private String secondSnowSheetName;



    @Autowired
    public Utils(WindSheetUtil windSheetUtil) {
        this.windSheetUtil = windSheetUtil;
    }



    // Метод для получения Id листа и range для расчетных данных
    public HashMap <String, String> getSheetData(boolean inputDataNeed, String sheetName) {
        if (windSheetName.equals(sheetName)) {
            return windSheetUtil.getData(inputDataNeed);
        } else if (firstSnowSheetName.equals(sheetName)) {
            // TODO: написать утилиту для снега 1
        } else if (secondSnowSheetName.equals(sheetName)) {
            // TODO: написать утилиту для снега 2
        }

        return null;
    }



    // Метод для преобразования ответа (колонок для вводных данных) от googleApi в HashMap
    public ResponseEntity<HashMap<String, Float>> convertInputCellsToMap(ResponseEntity<GeneralSheetDto> inputCells, String sheetName) {
        if (Objects.requireNonNull(inputCells.getBody()).getValues() != null) {
            Object [][] values = inputCells.getBody().getValues();

            if (windSheetName.equals(sheetName)) {
                return windSheetUtil.convertCellsToWindParams(values, true);
            } else if (firstSnowSheetName.equals(sheetName)) {
                // TODO: написать утилиту для снега 1
            } else if (secondSnowSheetName.equals(sheetName)) {
                // TODO: написать утилиту для снега 2
            }

            throw new IllegalArgumentException("sheetName: is not correct");

        }
        throw new IllegalArgumentException("something went wrong");
    }

    // Метод для преобразования ответа (колонок с расчетными значениями) от googleApi в HashMap
    public ResponseEntity<HashMap<String, Float>> convertResultCellsToMap(ResponseEntity<GeneralSheetDto> resultCells, String sheetName) {
        if (Objects.requireNonNull(resultCells.getBody()).getValues() != null) {
            Object [][] values = resultCells.getBody().getValues();

            if (windSheetName.equals(sheetName)) {
                return windSheetUtil.convertCellsToWindParams(values,false);
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
