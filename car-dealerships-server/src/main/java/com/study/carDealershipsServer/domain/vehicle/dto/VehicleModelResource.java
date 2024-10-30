package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.VehicleBrand;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Builder
@Jacksonized
public record VehicleModelResource(

        VehicleBrand vehicleBrand,
        String modelName,
        Integer startProduction,
        Integer endProduction
) {
}
