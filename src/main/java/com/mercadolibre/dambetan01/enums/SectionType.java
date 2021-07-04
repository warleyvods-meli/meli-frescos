package com.mercadolibre.dambetan01.enums;

public enum SectionType {
    CHILLED(10.0),
    FROZEN(0.0),
    AIRY(18.0);

    private final Double temperature;

    SectionType(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }
}
