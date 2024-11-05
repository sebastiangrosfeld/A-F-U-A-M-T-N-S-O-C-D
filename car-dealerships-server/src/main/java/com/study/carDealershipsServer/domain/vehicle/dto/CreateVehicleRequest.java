package com.study.carDealershipsServer.domain.vehicle.dto;

import com.study.carDealershipsServer.common.VehicleType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;

@Builder
@Jacksonized
public record CreateVehicleRequest(

        @NotBlank
        String vinNumber,
        @NotNull
        VehicleType vehicleType,
        @NotNull
        String color,
        @NotNull
        String bodyLine,
        @NotNull @Positive
        Integer yearOfProduction,
        @NotNull
        Date registrationDate,
        @NotBlank
        String modelName,
        @Max(3L)
        List<String> engines,
        @Max(3L)
        List<CreateEngineRequest> newEngines
) {
}
