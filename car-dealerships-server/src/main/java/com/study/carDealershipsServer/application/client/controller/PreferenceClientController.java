package com.study.carDealershipsServer.application.client.controller;

import com.study.carDealershipsServer.application.client.useCase.PreferenceClientInterface;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleDTO;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.study.carDealershipsServer.common.Constants.CLIENT_PREFIX;
import static com.study.carDealershipsServer.common.Constants.PREFERENCES_PREFIX;

@RestController
@RequestMapping(CLIENT_PREFIX + PREFERENCES_PREFIX)
@RequiredArgsConstructor
public class PreferenceClientController {

    private final PreferenceClientInterface preferenceClientInterface;

    @PostMapping
    public ResponseEntity<Void> createPreference(@RequestBody PreferenceVehicleDTO preferenceVehicleDTO) {
        preferenceClientInterface.createPreference(preferenceVehicleDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{clientId}/all")
    public ResponseEntity<List<PreferenceVehicleResource>> getPreferences(@PathVariable UUID clientId) {
        var preferences = preferenceClientInterface.getPreferences(clientId);
        return ResponseEntity.ok(preferences);
    }

    @GetMapping("/{preferenceId}")
    public ResponseEntity<PreferenceVehicleResource> getPreference(@PathVariable UUID preferenceId) {
        var preference = preferenceClientInterface.getPreference(preferenceId);
        return ResponseEntity.ok(preference);
    }

    @DeleteMapping("/{preferenceId}")
    public ResponseEntity<Void> deletePreferences(@PathVariable UUID preferenceId) {
        preferenceClientInterface.deletePreference(preferenceId);
        return ResponseEntity.noContent().build();
    }
}
