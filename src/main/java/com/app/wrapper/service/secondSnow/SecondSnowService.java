package com.app.wrapper.service.secondSnow;

import org.springframework.beans.factory.annotation.Value;

public class SecondSnowService {


    @Value("${sheets.snow2.sheet-id}")
    private String secondSnowSheetId;

    @Value("${sheets.snow2.input-range}")
    private String secondSnowInputRange;
}
