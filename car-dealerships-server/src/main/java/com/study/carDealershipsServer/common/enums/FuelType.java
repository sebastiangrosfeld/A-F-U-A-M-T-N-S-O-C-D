package com.study.carDealershipsServer.common.enums;

public enum FuelType {

    DIESEL("Diesel"),
    PETROL("Petrol"),
    HYDROGEN("Hydrogen"),
    ELECTRIC("Electric");

    final String type;

    FuelType(String type) {
        this.type = type;
    }
}
