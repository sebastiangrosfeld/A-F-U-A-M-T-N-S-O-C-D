package com.study.carDealershipsServer.application.client.useCase;

import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleDTO;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleResource;

import java.util.List;
import java.util.UUID;

public interface PreferenceUseCases {

    void createPreference(PreferenceVehicleDTO vehicleDTO);

    List<PreferenceVehicleResource> getPreferences(UUID clientId);

    void deletePreference(UUID preferenceId);

    PreferenceVehicleResource getPreference(UUID preferenceId);
}
