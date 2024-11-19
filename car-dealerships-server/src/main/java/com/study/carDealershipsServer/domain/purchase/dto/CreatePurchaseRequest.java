package com.study.carDealershipsServer.domain.purchase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Jacksonized
@Builder
public record CreatePurchaseRequest(
        @NotNull
        UUID managerId,
        @NotNull
        BigDecimal price,
        @NotBlank
        String vinNumber,
        @NotBlank
        String email
) {
}
