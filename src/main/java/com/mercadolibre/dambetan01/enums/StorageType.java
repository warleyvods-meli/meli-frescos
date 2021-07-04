package com.mercadolibre.dambetan01.enums;

public enum StorageType {
    CHILLED(10.0),
    FROZEN(0.0),
    AIRY(18.0);

    private final Double temperature;

    StorageType(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }
}
