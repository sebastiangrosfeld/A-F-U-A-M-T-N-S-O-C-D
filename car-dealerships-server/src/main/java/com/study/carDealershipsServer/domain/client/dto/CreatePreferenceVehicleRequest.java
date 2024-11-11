package com.study.carDealershipsServer.domain.client.dto;

import com.study.carDealershipsServer.common.enums.VehicleBrand;
import com.study.carDealershipsServer.common.enums.VehicleType;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;


@Jacksonized
@Builder
public record CreatePreferenceVehicleRequest(
        VehicleType vehicleType,
        UUID clientId,
        VehicleBrand vehicleBrand,
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
