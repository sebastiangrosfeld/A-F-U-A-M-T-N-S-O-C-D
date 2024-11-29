package com.study.carDealershipsServer.domain.client.vehiclePreference.mapper;

import com.study.carDealershipsServer.domain.client.vehiclePreference.dto.CreatePreferenceVehicleRequest;
import com.study.carDealershipsServer.domain.client.vehiclePreference.dto.PreferenceVehicleResource;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.client.vehiclePreference.entity.VehiclePreference;
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

    public VehiclePreference mapDTOToEntity(CreatePreferenceVehicleRequest createPreferenceVehicleRequest) {
        Client clientProxy = entityManager.getReference(Client.class, createPreferenceVehicleRequest.clientId());
        VehicleModel model = vehicleModelRepository.findVehicleModelByModelName(createPreferenceVehicleRequest.modelName())
                .orElse(null);
        return VehiclePreference.builder()
                .client(clientProxy)
                .vehicleType(createPreferenceVehicleRequest.vehicleType())
                .vehicleModel(model)
                .vehicleBrand(createPreferenceVehicleRequest.vehicleBrand())
                .minimalPower(createPreferenceVehicleRequest.minimalPower())
                .maximalPower(createPreferenceVehicleRequest.maximalPower())
                .minimalMileage(createPreferenceVehicleRequest.minimalMileage())
                .maximalMileage(createPreferenceVehicleRequest.maximalMileage())
                .startProduction(createPreferenceVehicleRequest.startProduction())
                .endProduction(createPreferenceVehicleRequest.endProduction())
                .color(createPreferenceVehicleRequest.color())
                .bodyLine(createPreferenceVehicleRequest.bodyLine())
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
