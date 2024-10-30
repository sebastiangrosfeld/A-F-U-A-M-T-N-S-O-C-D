package com.study.carDealershipsServer.domain.client.dto;

import com.study.carDealershipsServer.common.VehicleBrand;
import com.study.carDealershipsServer.common.VehicleType;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleModelResource;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PreferenceVehicleResource(

        UUID id,
        String vehicleType,
        VehicleModelResource vehicleModel,
        String vehicleBrand,
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
