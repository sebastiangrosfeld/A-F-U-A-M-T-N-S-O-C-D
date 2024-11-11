package com.study.carDealershipsServer.domain.client.dto;

import com.study.carDealershipsServer.common.enums.VehicleBrand;
import com.study.carDealershipsServer.common.enums.VehicleType;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleModelResource;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PreferenceVehicleResource(

        UUID id,
        VehicleType vehicleType,
        VehicleModelResource vehicleModel,
        VehicleBrand vehicleBrand,
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
