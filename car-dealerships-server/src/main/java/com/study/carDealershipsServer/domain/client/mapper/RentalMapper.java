package com.study.carDealershipsServer.domain.client.mapper;

import com.study.carDealershipsServer.common.enums.RentalStatus;
import com.study.carDealershipsServer.domain.client.dto.BookRentalRequest;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.client.repository.ClientRepository;
import com.study.carDealershipsServer.domain.manager.entity.Rental;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalMapper {

    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final EntityManager entityManager;

    public Rental bookRequestToRental(BookRentalRequest bookRentalRequest, UUID clientId) {
        var vehicleId = vehicleRepository.getIdByVinNumber(bookRentalRequest.vinNumber());
        Vehicle vehicleProxy = entityManager.find(Vehicle.class, vehicleId);
        Client clientProxy = entityManager.find(Client.class, clientId);
        return Rental.builder()
                .rentalId(UUID.randomUUID())
                .status(RentalStatus.ACTIVE)
                .bookedStartTime(bookRentalRequest.bookedStartTime())
                .bookedEndTime(bookRentalRequest.bookedEndTime())
                .vehicle(vehicleProxy)
                .client(clientProxy)
                .build();
    }
}
