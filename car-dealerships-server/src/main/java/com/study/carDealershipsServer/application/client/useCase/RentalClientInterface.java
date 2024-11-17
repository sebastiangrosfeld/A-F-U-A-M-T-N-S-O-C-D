package com.study.carDealershipsServer.application.client.useCase;

import com.study.carDealershipsServer.domain.client.dto.BookRentalRequest;

import java.util.UUID;

public interface RentalClientInterface {

    void bookRental(BookRentalRequest bookRentalRequest, UUID clientId);

    void deleteBookRental(UUID rentalId);
}
