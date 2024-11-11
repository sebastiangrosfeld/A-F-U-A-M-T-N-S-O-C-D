package com.study.carDealershipsServer.common.enums;

public enum PurchaseStatus {

    ACTIVE("ACTIVE"),
    FINISHED("FINISHED"),
    DECLINE("DECLINED");

    final String name;

    PurchaseStatus(String brandName) {
        this.name = brandName;
    }
}
