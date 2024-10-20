package com.study.carDealershipsServer.common;

public enum CarBrands {

    VOLVO("Volvo"),
    BMW("BMW"),
    AUDI("Audi"),
    VOLKSWAGEN("Volkswagen"),
    SKODA("Skoda"),
    FIAT("Fiat"),
    OPEL("Opel")

    ;

    String name;

    CarBrands(String brandName) {
        this.name = brandName;
    }
}
