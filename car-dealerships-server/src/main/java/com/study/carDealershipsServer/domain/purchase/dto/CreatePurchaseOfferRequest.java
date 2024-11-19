package com.study.carDealershipsServer.domain.purchase.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Builder
public record CreatePurchaseOfferRequest(

        BigDecimal price,
        String vehicleVin
) {
}
