package com.study.carDealershipsServer.domain.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;


@Jacksonized
@Builder
public record PreferenceVehicleDTO(
        String vehicleType,
        UUID clientId,
        String vehicleBrand,
        String modelName,
        Integer minimalPower,
        Integer maximalPower,
        Integer minimalMileage,
        Integer maximalMileage,
        Integer startProduction,
        Integer endProduction,
        String color,
        String bodyLine
) {
}
