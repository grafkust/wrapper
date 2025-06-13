package com.app.wrapper.util.firstSnow;

import org.springframework.beans.factory.annotation.Value;

public class FirstSnowSheetUtil {


    @Value("${sheets.snow1.sheet-id}")
    private String firstSnowSheetId;

    @Value("${sheets.snow1.input-range}")
    private String firstSnowInputRange;




}
