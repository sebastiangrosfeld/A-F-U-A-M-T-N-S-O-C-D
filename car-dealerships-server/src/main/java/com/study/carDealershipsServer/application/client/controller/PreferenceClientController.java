package com.study.carDealershipsServer.application.client.controller;

import com.study.carDealershipsServer.application.client.useCase.PreferenceClientInterface;
import com.study.carDealershipsServer.domain.client.dto.CreatePreferenceVehicleRequest;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.study.carDealershipsServer.application.authentication.service.AuthService.getAuthUserId;
import static com.study.carDealershipsServer.common.Constants.CLIENT_PREFIX;
import static com.study.carDealershipsServer.common.Constants.PREFERENCES_PREFIX;

@RestController
@RequestMapping(CLIENT_PREFIX + PREFERENCES_PREFIX)
@RequiredArgsConstructor

public class PreferenceClientController {

    private final PreferenceClientInterface preferenceClientInterface;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Void> createPreference(@RequestBody CreatePreferenceVehicleRequest createPreferenceVehicleRequest) {
        preferenceClientInterface.createPreference(createPreferenceVehicleRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<PreferenceVehicleResource>> getPreferences() {
        var clientId = getAuthUserId();
        var preferences = preferenceClientInterface.getPreferences(clientId);
        return ResponseEntity.ok(preferences);
    }

    @GetMapping("/{preferenceId}")
    public ResponseEntity<PreferenceVehicleResource> getPreference(@PathVariable UUID preferenceId) {
        var preference = preferenceClientInterface.getPreference(preferenceId);
        return ResponseEntity.ok(preference);
    }

    @DeleteMapping("/{preferenceId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Void> deletePreferences(@PathVariable UUID preferenceId) {
        preferenceClientInterface.deletePreference(preferenceId);
        return ResponseEntity.noContent().build();
    }
}
