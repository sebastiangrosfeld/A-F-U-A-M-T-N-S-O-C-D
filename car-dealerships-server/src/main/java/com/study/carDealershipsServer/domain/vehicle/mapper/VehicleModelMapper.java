package com.study.carDealershipsServer.domain.vehicle.mapper;

import com.study.carDealershipsServer.domain.vehicle.dto.VehicleModelResource;
import com.study.carDealershipsServer.domain.vehicle.entity.VehicleModel;
import org.springframework.stereotype.Service;

@Service
public class VehicleModelMapper {

    public VehicleModelResource entityToResource(VehicleModel vehicleModel) {
        if (vehicleModel == null) {
            return null;
        }
        return VehicleModelResource.builder()
                .vehicleBrand(vehicleModel.getVehicleBrand())
                .modelName(vehicleModel.getModelName())
                .startProduction(vehicleModel.getStartProduction())
                .endProduction(vehicleModel.getEndProduction())
                .build();
    }
}
