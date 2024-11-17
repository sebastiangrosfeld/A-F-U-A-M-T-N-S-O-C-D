package com.study.carDealershipsServer.domain.client.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Builder
public record BookRentalRequest(
        LocalDateTime bookedStartTime,
        LocalDateTime bookedEndTime,
        String vinNumber
) {
}
