package com.study.carDealershipsServer.application.client.controller;

import com.study.carDealershipsServer.application.client.useCase.PreferenceUseCases;
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
public class PreferenceController {

    private final PreferenceUseCases preferenceUseCases;

    @PostMapping
    public ResponseEntity<Void> createPreference(@RequestBody PreferenceVehicleDTO preferenceVehicleDTO) {
        preferenceUseCases.createPreference(preferenceVehicleDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<PreferenceVehicleResource>> getPreferences(@PathVariable UUID clientId) {
        var preferences = preferenceUseCases.getPreferences(clientId);
        return ResponseEntity.ok(preferences);
    }

    @GetMapping("/{preferenceId}")
    public ResponseEntity<PreferenceVehicleResource> getPreference(@PathVariable UUID preferenceId) {
        var preference = preferenceUseCases.getPreference(preferenceId);
        return ResponseEntity.ok(preference);
    }

    @DeleteMapping("/{preferenceId}")
    public ResponseEntity<Void> deletePreferences(@PathVariable UUID preferenceId) {
        preferenceUseCases.deletePreference(preferenceId);
        return ResponseEntity.noContent().build();
    }
}
