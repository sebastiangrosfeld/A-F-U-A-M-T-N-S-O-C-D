package com.study.carDealershipsServer.common;

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
