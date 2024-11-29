package com.study.carDealershipsServer.common.enums;

public enum RentalStatus {

    BOOKED("BOOKED"),
    ACTIVE("ACTIVE"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED"),
    DECLINED("DECLINED");

    final String name;

    RentalStatus(String brandName) {
        this.name = brandName;
    }
}
