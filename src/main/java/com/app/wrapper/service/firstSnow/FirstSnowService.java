package com.app.wrapper.service.firstSnow;

import org.springframework.beans.factory.annotation.Value;

public class FirstSnowService {


    @Value("${sheets.snow1.sheet-id}")
    private String firstSnowSheetId;

    @Value("${sheets.snow1.input-range}")
    private String firstSnowInputRange;




}
