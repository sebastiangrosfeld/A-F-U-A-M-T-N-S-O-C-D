package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.RentalOfferManagerInterface;
import com.study.carDealershipsServer.domain.manager.dto.CreateRentalOfferRequest;
import com.study.carDealershipsServer.domain.manager.dto.RentalOfferResource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.study.carDealershipsServer.application.authentication.service.AuthService.getAuthUserId;
import static com.study.carDealershipsServer.common.Constants.MANAGER_PREFIX;
import static com.study.carDealershipsServer.common.Constants.RENTAL_OFFER_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER_PREFIX + RENTAL_OFFER_PREFIX)
public class RentalOfferManagerController {

    private final RentalOfferManagerInterface rentalOfferManager;

    @PostMapping
    public ResponseEntity<Void> createRentalOffer(CreateRentalOfferRequest request) {
        var managerId = getAuthUserId();
        rentalOfferManager.createRentalOffer(request, managerId);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<RentalOfferResource>> getRentalOffers(Pageable pageable) {
        var rentalOffers = rentalOfferManager.getRentalOffers(pageable);
        return ResponseEntity.ok(rentalOffers);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<RentalOfferResource> getRentalOffer(@PathVariable String vin) {
        var rentalOffer = rentalOfferManager.getRentalOffer(vin);
        return ResponseEntity.ok(rentalOffer);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> deleteRentalOffer(@PathVariable String vin) {
        rentalOfferManager.deleteRentalOffer(vin);
        return ResponseEntity.noContent().build();
    }
}
