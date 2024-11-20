package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.enums.VehicleBrand;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record CreateVehicleModelRequest(
        VehicleBrand brand,
        String modelName,
        Integer startProduction,
        Integer endProduction
) {
}
