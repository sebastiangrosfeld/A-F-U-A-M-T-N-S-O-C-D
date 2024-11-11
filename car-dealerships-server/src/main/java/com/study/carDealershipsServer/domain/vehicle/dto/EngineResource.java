package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.enums.FuelType;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record EngineResource(
        String name,
        FuelType fuelType,
        Integer numberOfCylinders,
        Integer horsePower,
        Integer torque
) {
}
