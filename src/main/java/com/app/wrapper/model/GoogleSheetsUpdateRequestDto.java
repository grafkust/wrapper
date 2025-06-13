package com.app.wrapper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleSheetsUpdateRequestDto {


    @JsonProperty("values")
    private List<List<String>> values;

    public GoogleSheetsUpdateRequestDto() {
        this.values = new ArrayList<>();
    }

    public List<List<String>> getValues() {
        return values;
    }


}
