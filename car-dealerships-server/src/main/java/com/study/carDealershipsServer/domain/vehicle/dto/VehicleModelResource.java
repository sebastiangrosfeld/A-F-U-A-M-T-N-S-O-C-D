package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.enums.VehicleBrand;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record VehicleModelResource(

        VehicleBrand vehicleBrand,
        String modelName,
        Integer startProduction,
        Integer endProduction
) {
}
