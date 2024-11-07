package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.VehicleType;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;

@Jacksonized
@Builder
public record VehicleResource(
        String vinNumber,
        VehicleType type,
        String color,
        String bodyLine,
        Integer yearOfProduction,
        Date registrationDate,
        VehicleModelResource vehicleModel,
        List<EngineResource> engines

) {
}
