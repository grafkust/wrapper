package com.app.wrapper.model.wind;


public enum WindInputParams implements WindParam{

    length("length", 0, 0),
    rise("rise", 1, 0),
    height("height", 2, 0),
    safety("safety", 3, 0),
    pressure("pressure", 4, 0),
    coefA("coefA", 5, 0),
    coefB("coefB", 6, 0),
    coefC("coefC", 7, 0);

    private final String key;
    private final Integer row;
    private final Integer col;

    WindInputParams(String key, Integer order, Integer col) {
        this.key = key;
        this.row = order;
        this.col = col;
    }


    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Integer getRow() {
        return row;
    }

    @Override
    public Integer getCol() {
        return col;
    }
}