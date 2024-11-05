package com.study.carDealershipsServer.domain.vehicle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record CreateEngineRequest(
        @NotBlank
        String engineName,
        @NotBlank
        String typeOfFuel,
        @NotNull
        Integer numberOfCylinders,
        @NotNull
        Integer horsePower,
        @NotNull
        Integer torque
) {
}
