package com.app.wrapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoogleSheetsUpdateResponseDto {

    @JsonProperty("spreadsheetId")
    private String spreadsheetId;

    @JsonProperty("updatedRange")
    private String updatedRange;

    @JsonProperty("updatedRows")
    private Integer updatedRows;

    @JsonProperty("updatedColumns")
    private Integer updatedColumns;

    @JsonProperty("updatedCells")
    private Integer updatedCells;

}
