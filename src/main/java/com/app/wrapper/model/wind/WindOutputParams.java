package com.app.wrapper.model.wind;

public enum WindOutputParams implements WindParam{

    // Первая строка (индекс 0)
    res_1 ("res_1", 0, 0),
    res_2 ("res_2", 0, 1),
    res_3 ("res_3", 0, 2),
    res_4 ("res_4", 0, 3),
    res_5 ("res_5", 0, 4),
    res_6 ("res_6", 0, 5),
    res_7 ("res_7", 0, 6),
    res_8 ("res_8", 0, 7),
    res_9 ("res_9", 0, 8),
    res_10 ("res_10", 0, 9),
    res_11 ("res_11", 0, 10),
    res_12 ("res_12", 0, 11),
    res_13 ("res_13", 0, 12),
    res_14 ("res_14", 0, 13),
    res_15 ("res_15", 0, 14),
    res_16 ("res_16", 0, 15),
    res_17 ("res_17", 0, 16),
    res_18 ("res_18", 0, 17),
    res_19 ("res_19", 0, 18),
    res_20 ("res_20", 0, 19),
    res_21 ("res_21", 0, 20),

    // Вторая строка (индекс 1)
    res_22 ("res_22", 1, 0),
    res_23 ("res_23", 1, 1),
    res_24 ("res_24", 1, 2),
    res_25 ("res_25", 1, 3),
    res_26 ("res_26", 1, 4),
    res_27 ("res_27", 1, 5),
    res_28 ("res_28", 1, 6),
    res_29 ("res_29", 1, 7),
    res_30 ("res_30", 1, 8),
    res_31 ("res_31", 1, 9),
    res_32 ("res_32", 1, 10),
    res_33 ("res_33", 1, 11),
    res_34 ("res_34", 1, 12),
    res_35 ("res_35", 1, 13),
    res_36 ("res_36", 1, 14),
    res_37 ("res_37", 1, 15),
    res_38 ("res_38", 1, 16),
    res_39 ("res_39", 1, 17),
    res_40 ("res_40", 1, 18),
    res_41 ("res_41", 1, 19),
    res_42 ("res_42", 1, 20);


    private final String key;
    private final Integer row;
    private final Integer col;

    WindOutputParams(final String key, final Integer row, final Integer col) {
        this.key = key;
        this.row = row;
        this.col = col;
    }

    public String getKey() {
        return key;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

}
