package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.enums.FuelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record CreateEngineRequest(
        @NotBlank
        String engineName,
        @NotBlank
        FuelType typeOfFuel,
        @NotNull
        @Positive
        Integer numberOfCylinders,
        @NotNull
        @Positive
        Integer horsePower,
        @NotNull
        @Positive
        Integer torque
) {
}
