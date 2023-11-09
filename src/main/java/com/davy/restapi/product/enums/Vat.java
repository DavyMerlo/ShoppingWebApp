package com.davy.restapi.product.enums;

public enum Vat {
    NONE(0),
    REDUCED_RATE(6),
    INTERMEDIATE_RATE(12),
    STANDARD_RATE(21);

    private final int value;

    Vat(int value) {
        this.value = value;
    }

    public int getVatValue(){
        return value;
    }
}
