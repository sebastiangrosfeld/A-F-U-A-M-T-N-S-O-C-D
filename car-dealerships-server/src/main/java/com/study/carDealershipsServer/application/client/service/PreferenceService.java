package com.study.carDealershipsServer.application.client.service;

import com.study.carDealershipsServer.application.client.useCase.PreferenceUseCases;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleDTO;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleResource;
import com.study.carDealershipsServer.domain.client.mapper.VehiclePreferenceMapper;
import com.study.carDealershipsServer.domain.client.repository.ClientRepository;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleModelRepository;
import com.study.carDealershipsServer.domain.client.repository.VehiclePreferenceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class PreferenceService implements PreferenceUseCases {

    private final VehiclePreferenceRepository vehiclePreferenceRepository;
    private final ClientRepository clientRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final VehiclePreferenceMapper vehiclePreferenceMapper;


    @Override
    @Transactional
    public void createPreference(PreferenceVehicleDTO preferenceVehicleDTO) {
        validatePreference(preferenceVehicleDTO);
        var preference = vehiclePreferenceMapper.mapDTOToEntity(preferenceVehicleDTO);
        vehiclePreferenceRepository.save(preference);
    }

    @Override
    public List<PreferenceVehicleResource> getPreferences(UUID clientId) {
        var preferences = vehiclePreferenceRepository.findVehiclePreferencesByClientId(clientId).orElse(emptyList());
        return preferences.stream().map(vehiclePreferenceMapper::mapEntityToResource).toList();
    }

    @Override
    public void deletePreference(UUID preferenceId) {
        vehiclePreferenceRepository.deleteById(preferenceId);
    }

    @Override
    public PreferenceVehicleResource getPreference(UUID preferenceId) {
        var preference = vehiclePreferenceRepository.getVehiclePreferenceById(preferenceId)
                .orElseThrow(() -> new IllegalArgumentException("Preference not found"));
        return vehiclePreferenceMapper.mapEntityToResource(preference);
    }

    private void validatePreference(PreferenceVehicleDTO preferenceDTO) {
        if(!clientRepository.existsById(preferenceDTO.clientId())) {
            throw new RuntimeException("Client with id " + preferenceDTO.clientId() + " does not exist");
        }
        if (preferenceDTO.modelName() != null && !vehicleModelRepository.existsVehicleModelByModelName(preferenceDTO.modelName())) {
            throw new RuntimeException("Model with name " + preferenceDTO.modelName() + " does not exist");
        }
        if (vehiclePreferenceRepository.countVehiclePreferencesByClientId(preferenceDTO.clientId()) >= 5L) {
            throw new RuntimeException("Client with id " + preferenceDTO.clientId() + " has maximal number of vehicle preferences");
        }
    }
}
