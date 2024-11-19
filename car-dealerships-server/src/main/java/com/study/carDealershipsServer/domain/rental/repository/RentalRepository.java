package com.study.carDealershipsServer.domain.rental.repository;

import com.study.carDealershipsServer.domain.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    boolean existsByVehicleVinNumber(String vehicleVinNumber);

    void deleteByRentalId(UUID rentalId);

    boolean existsByRentalId(UUID rentalId);

}
