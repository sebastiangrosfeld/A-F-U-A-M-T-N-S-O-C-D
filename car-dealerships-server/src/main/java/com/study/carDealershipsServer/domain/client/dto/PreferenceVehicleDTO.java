package com.study.carDealershipsServer.domain.client.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;


public record PreferenceVehicleDTO(

        String vehicleType,
        @NotNull
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
