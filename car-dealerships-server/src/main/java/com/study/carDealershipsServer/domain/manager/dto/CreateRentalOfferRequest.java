package com.study.carDealershipsServer.domain.manager.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Builder
public record CreateRentalOfferRequest(

        BigDecimal pricePerHour,
        String vehicleVin

) {
}
