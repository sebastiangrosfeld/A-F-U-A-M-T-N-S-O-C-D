package com.study.carDealershipsServer.application.client.useCase;

import com.study.carDealershipsServer.domain.client.dto.CreatePreferenceVehicleRequest;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleResource;

import java.util.List;
import java.util.UUID;

public interface PreferenceClientInterface {

    void createPreference(CreatePreferenceVehicleRequest vehicleDTO);

    List<PreferenceVehicleResource> getPreferences(UUID clientId);

    void deletePreference(UUID preferenceId);

    PreferenceVehicleResource getPreference(UUID preferenceId);
}
