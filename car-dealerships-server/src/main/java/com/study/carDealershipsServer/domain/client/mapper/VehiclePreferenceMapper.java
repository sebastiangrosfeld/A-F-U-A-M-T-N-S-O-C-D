package com.study.carDealershipsServer.domain.client.mapper;

import com.study.carDealershipsServer.common.VehicleBrand;
import com.study.carDealershipsServer.common.VehicleType;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleDTO;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleResource;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.client.entity.VehiclePreference;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleModelResource;
import com.study.carDealershipsServer.domain.vehicle.mapper.VehicleModelMapper;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleModelRepository;
import com.study.carDealershipsServer.domain.vehicle.entity.VehicleModel;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehiclePreferenceMapper {

    private final EntityManager entityManager;
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleModelMapper vehicleModelMapper;

    public VehiclePreference mapDTOToEntity(PreferenceVehicleDTO preferenceVehicleDTO) {
        Client clientProxy = entityManager.getReference(Client.class, preferenceVehicleDTO.clientId());
        VehicleModel model = vehicleModelRepository.findVehicleModelByModelName(preferenceVehicleDTO.modelName())
                .orElse(null);
        return VehiclePreference.builder()
                .client(clientProxy)
                .vehicleType(preferenceVehicleDTO.vehicleType())
                .vehicleModel(model)
                .vehicleBrand(preferenceVehicleDTO.vehicleBrand())
                .minimalPower(preferenceVehicleDTO.minimalPower())
                .maximalPower(preferenceVehicleDTO.maximalPower())
                .minimalMileage(preferenceVehicleDTO.minimalMileage())
                .maximalMileage(preferenceVehicleDTO.maximalMileage())
                .startProduction(preferenceVehicleDTO.startProduction())
                .endProduction(preferenceVehicleDTO.endProduction())
                .color(preferenceVehicleDTO.color())
                .bodyLine(preferenceVehicleDTO.bodyLine())
                .build();
    }

    public PreferenceVehicleResource mapEntityToResource(VehiclePreference vehiclePreference) {
        VehicleModelResource vehicleModelResource = vehicleModelMapper.entityToResource(vehiclePreference.getVehicleModel());
        return PreferenceVehicleResource.builder()
                .id(vehiclePreference.getId())
                .vehicleType(vehiclePreference.getVehicleType())
                .vehicleModel(vehicleModelResource)
                .vehicleBrand(vehiclePreference.getVehicleBrand())
                .maximalPower(vehiclePreference.getMaximalPower())
                .minimalPower(vehiclePreference.getMinimalPower())
                .minimalMileage(vehiclePreference.getMinimalMileage())
                .maximalMileage(vehiclePreference.getMaximalMileage())
                .startProduction(vehiclePreference.getStartProduction())
                .endProduction(vehiclePreference.getEndProduction())
                .color(vehiclePreference.getColor())
                .bodyLine(vehiclePreference.getBodyLine())
                .build();
    }
}
