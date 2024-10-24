package com.study.carDealershipsServer.common;

public enum VehicleType {

    CAR("Car"),
    MOTORCYCLE("Motorcycle"),
    QUAD("Quad");

    final String type;

    VehicleType(String type) {
        this.type = type;
    }
}
