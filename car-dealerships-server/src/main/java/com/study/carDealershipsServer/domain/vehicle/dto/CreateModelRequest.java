package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.enums.VehicleBrand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record CreateModelRequest(
        @NotBlank
        String modelName,
        @NotNull
        VehicleBrand vehicleBrand,
        @NotNull
        Integer startProduction,
        @NotNull
        Integer endProduction
) {
}
