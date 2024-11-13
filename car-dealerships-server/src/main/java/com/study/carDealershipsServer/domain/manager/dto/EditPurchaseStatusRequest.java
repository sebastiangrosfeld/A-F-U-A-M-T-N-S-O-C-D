package com.study.carDealershipsServer.domain.manager.dto;

import com.study.carDealershipsServer.common.enums.PurchaseStatus;

import java.util.UUID;

public record EditPurchaseStatusRequest(
        UUID purchaseId,
        PurchaseStatus purchaseStatus
) {
}
