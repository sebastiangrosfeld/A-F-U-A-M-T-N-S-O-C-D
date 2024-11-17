package com.study.carDealershipsServer.domain.manager.dto;

import com.study.carDealershipsServer.domain.vehicle.dto.VehicleResource;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Builder
public record RentalOfferResource(
        BigDecimal pricePerHour,
        ManagerResource managerResource,
        VehicleResource vehicleResource
) {
}
