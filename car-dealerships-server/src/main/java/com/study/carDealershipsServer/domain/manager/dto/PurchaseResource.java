package com.study.carDealershipsServer.domain.manager.dto;

import com.study.carDealershipsServer.common.enums.PurchaseStatus;
import com.study.carDealershipsServer.domain.client.dto.ClientResource;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleResource;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Jacksonized
@Builder
public record PurchaseResource(
        UUID purchaseId,
        LocalDate purchaseDate,
        PurchaseStatus status,
        BigDecimal price,
        ClientResource clientResource,
        ManagerResource managerResource,
        VehicleResource vehicleResource
) {
}