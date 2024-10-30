package com.study.carDealershipsServer.domain.client.repository;

import com.study.carDealershipsServer.domain.client.entity.VehiclePreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehiclePreferenceRepository extends JpaRepository<VehiclePreference, Long> {

    Long countVehiclePreferencesByClientId(UUID clientId);
    Optional<List<VehiclePreference>> findVehiclePreferencesByClientId(UUID clientId);
    Optional<VehiclePreference> getVehiclePreferenceById(UUID id);
    void deleteById(UUID id);
}
