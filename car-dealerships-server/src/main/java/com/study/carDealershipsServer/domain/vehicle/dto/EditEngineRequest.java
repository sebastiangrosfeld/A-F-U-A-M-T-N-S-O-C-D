package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.enums.FuelType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record EditEngineRequest(
        @NotBlank
        String oldEngineName,
        String newEngineName,
        FuelType typeOfFuel,
        Integer numberOfCylinders,
        Integer horsePower,
        Integer torque
) {
}
