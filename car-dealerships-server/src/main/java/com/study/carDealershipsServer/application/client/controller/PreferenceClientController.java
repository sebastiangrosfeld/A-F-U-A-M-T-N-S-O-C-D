package com.study.carDealershipsServer.application.client.controller;

import com.study.carDealershipsServer.application.client.useCase.PreferenceClientFacade;
import com.study.carDealershipsServer.domain.client.vehiclePreference.dto.CreatePreferenceVehicleRequest;
import com.study.carDealershipsServer.domain.client.vehiclePreference.dto.PreferenceVehicleResource;
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

    private final PreferenceClientFacade preferenceClientFacade;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Void> createPreference(@RequestBody CreatePreferenceVehicleRequest createPreferenceVehicleRequest) {
        preferenceClientFacade.createPreference(createPreferenceVehicleRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<PreferenceVehicleResource>> getPreferences() {
        var clientId = getAuthUserId();
        var preferences = preferenceClientFacade.getPreferences(clientId);
        return ResponseEntity.ok(preferences);
    }

    @GetMapping("/{preferenceId}")
    public ResponseEntity<PreferenceVehicleResource> getPreference(@PathVariable UUID preferenceId) {
        var preference = preferenceClientFacade.getPreference(preferenceId);
        return ResponseEntity.ok(preference);
    }

    @DeleteMapping("/{preferenceId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Void> deletePreferences(@PathVariable UUID preferenceId) {
        preferenceClientFacade.deletePreference(preferenceId);
        return ResponseEntity.noContent().build();
    }
}
