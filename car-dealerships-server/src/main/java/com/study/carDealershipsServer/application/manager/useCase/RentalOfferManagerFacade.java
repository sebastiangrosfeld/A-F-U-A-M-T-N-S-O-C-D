package com.study.carDealershipsServer.application.manager.useCase;

import com.study.carDealershipsServer.domain.rental.dto.CreateRentalOfferRequest;
import com.study.carDealershipsServer.domain.rental.dto.RentalOfferResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RentalOfferManagerFacade {
    
    void createRentalOffer(CreateRentalOfferRequest request, UUID managerId);

    Page<RentalOfferResource> getRentalOffers(Pageable pageable);

    RentalOfferResource getRentalOffer(String vin);

    void deleteRentalOffer(String vin);
}
