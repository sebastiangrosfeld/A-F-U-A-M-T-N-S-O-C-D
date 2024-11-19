package com.study.carDealershipsServer.domain.rental.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Builder
public record CreateRentalRequest(
        Long bookedStartTime,
        Long bookedEndTime,
        UUID clientId,
        String vinNumber
) {
}
