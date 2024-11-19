package com.study.carDealershipsServer.application.client.service;

import com.study.carDealershipsServer.application.client.useCase.RentalClientFacade;
import com.study.carDealershipsServer.common.errors.ServiceException;
import com.study.carDealershipsServer.domain.rental.dto.BookRentalRequest;
import com.study.carDealershipsServer.domain.rental.mapper.RentalMapper;
import com.study.carDealershipsServer.domain.rental.repository.RentalRepository;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalClientService implements RentalClientFacade {

    private final RentalRepository rentalRepository;
    private final VehicleRepository vehicleRepository;
    private final RentalMapper rentalMapper;

    @Override
    public void bookRental(BookRentalRequest bookRentalRequest, UUID clientId) {
        validateRental(bookRentalRequest);
        rentalRepository.save(rentalMapper.bookRequestToRental(bookRentalRequest, clientId));
    }

    @Override
    public void deleteBookRental(UUID rentalId) {
        if (!rentalRepository.existsByRentalId(rentalId)) {
            throw new ServiceException("Rental not exists.", HttpStatus.NOT_FOUND);
        }
        rentalRepository.deleteByRentalId(rentalId);
    }

    private void validateRental(BookRentalRequest bookRentalRequest) {
        if (!vehicleRepository.existsByVinNumber(bookRentalRequest.vinNumber())) {
            throw new ServiceException("Rental vehicle not exists.", HttpStatus.BAD_REQUEST );
        }
        if (LocalDateTime.now().isAfter(bookRentalRequest.bookedStartTime()) || LocalDateTime.now().isAfter(bookRentalRequest.bookedEndTime())) {
            throw new ServiceException("Invalid booking time.", HttpStatus.BAD_REQUEST );
        }
        if (bookRentalRequest.bookedStartTime().isAfter(bookRentalRequest.bookedEndTime())) {
            throw new ServiceException("Start time is after end time.", HttpStatus.BAD_REQUEST );
        }
    }
}
