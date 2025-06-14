package com.app.wrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
public class GeneralSheetDto {

    @JsonProperty("range")
    private String range;

    @JsonProperty("majorDimension")
    private String majorDimension;

    @Getter
    @JsonProperty("values")
    private Object[][] values;

}
