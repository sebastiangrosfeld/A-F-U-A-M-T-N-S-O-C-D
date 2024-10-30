package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.VehicleBrand;
import lombok.Builder;

import java.util.Date;

@Builder
public record VehicleModelResource(

        VehicleBrand vehicleBrand,
        String modelName,
        Date startProduction,
        Date endProduction
) {
}
