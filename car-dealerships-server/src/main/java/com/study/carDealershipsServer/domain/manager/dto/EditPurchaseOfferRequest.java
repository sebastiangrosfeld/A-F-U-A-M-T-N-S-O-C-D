package com.study.carDealershipsServer.domain.manager.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record EditPurchaseOfferRequest(
        String oldVinNumber,
        String newVinNumber,
        BigDecimal price,
        UUID managerId

) {
}
