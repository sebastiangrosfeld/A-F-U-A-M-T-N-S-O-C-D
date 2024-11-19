package com.study.carDealershipsServer.application.client.useCase;

import com.study.carDealershipsServer.domain.rental.dto.BookRentalRequest;

import java.util.UUID;

public interface RentalClientFacade {

    void bookRental(BookRentalRequest bookRentalRequest, UUID clientId);

    void deleteBookRental(UUID rentalId);
}
