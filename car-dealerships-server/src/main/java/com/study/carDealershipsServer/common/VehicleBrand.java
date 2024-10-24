package com.study.carDealershipsServer.common;

public enum VehicleBrand {

    VOLVO("Volvo"),
    BMW("BMW"),
    AUDI("Audi"),
    VOLKSWAGEN("Volkswagen"),
    SKODA("Skoda"),
    FIAT("Fiat"),
    OPEL("Opel")

    ;

    String name;

    VehicleBrand(String brandName) {
        this.name = brandName;
    }
}
