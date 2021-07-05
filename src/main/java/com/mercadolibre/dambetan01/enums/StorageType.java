package com.mercadolibre.dambetan01.enums;

public enum StorageType {

    AIRY(18.0){

        @Override
        public StorageType getNextStorage(){
            return AIRY;
        }

        @Override
        public StorageType getPreviousStorage(){
            return CHILLED;
        }
    },

    CHILLED(10.0){

        @Override
        public StorageType getNextStorage(){
            return AIRY;
        }

        @Override
        public StorageType getPreviousStorage(){
            return FROZEN;
        }
    },

    FROZEN(0.0){

        @Override
        public StorageType getNextStorage(){
            return CHILLED;
        }

        @Override
        public StorageType getPreviousStorage(){
            return FROZEN;
        }
    };

    private final Double temperature;

    StorageType(Double temperature) {
        this.temperature = temperature;
    }

    public abstract StorageType getNextStorage();

    public abstract StorageType getPreviousStorage();

    public Double getTemperature() {
        return temperature;
    };


}
