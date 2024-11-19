package com.study.carDealershipsServer.application.client.controller;

import com.study.carDealershipsServer.application.client.useCase.RentalClientFacade;
import com.study.carDealershipsServer.domain.rental.dto.BookRentalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.study.carDealershipsServer.application.authentication.service.AuthService.getAuthUserId;
import static com.study.carDealershipsServer.common.Constants.CLIENT_PREFIX;
import static com.study.carDealershipsServer.common.Constants.RENTALS_PREFIX;

@RestController
@RequestMapping(CLIENT_PREFIX + RENTALS_PREFIX)
@RequiredArgsConstructor
public class RentalClientController {

    private final RentalClientFacade rentalClientFacade;

    @PostMapping
    public ResponseEntity<Void> bookRental(@RequestBody BookRentalRequest bookRentalRequest) {
        var clientId = getAuthUserId();
        rentalClientFacade.bookRental(bookRentalRequest, clientId);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<Void> deleteBookRental(@PathVariable UUID rentalId) {
        rentalClientFacade.deleteBookRental(rentalId);
        return ResponseEntity.noContent().build();
    }
}
