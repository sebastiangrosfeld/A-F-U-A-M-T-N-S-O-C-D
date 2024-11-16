package com.study.carDealershipsServer.domain.manager.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Jacksonized
@Builder
public record CreatePurchaseOfferRequest(

        BigDecimal price,
        UUID managerId,
        String vehicleVin
) {
}