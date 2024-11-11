package com.study.carDealershipsServer.common.enums;

public enum RentalStatus {

    ACTIVE("ACTIVE"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED");

    final String name;

    RentalStatus(String brandName) {
        this.name = brandName;
    }
}
