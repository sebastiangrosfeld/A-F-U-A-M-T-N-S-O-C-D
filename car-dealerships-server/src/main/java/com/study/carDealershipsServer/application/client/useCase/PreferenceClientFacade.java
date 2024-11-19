package com.study.carDealershipsServer.application.client.useCase;

import com.study.carDealershipsServer.domain.vehiclePreference.dto.CreatePreferenceVehicleRequest;
import com.study.carDealershipsServer.domain.vehiclePreference.dto.PreferenceVehicleResource;

import java.util.List;
import java.util.UUID;

public interface PreferenceClientFacade {

    void createPreference(CreatePreferenceVehicleRequest vehicleDTO);

    List<PreferenceVehicleResource> getPreferences(UUID clientId);

    void deletePreference(UUID preferenceId);

    PreferenceVehicleResource getPreference(UUID preferenceId);
}
