package com.study.carDealershipsServer.application.manager.service;

import com.study.carDealershipsServer.application.manager.useCase.RentalOfferManagerFacade;
import com.study.carDealershipsServer.domain.rental.dto.CreateRentalOfferRequest;
import com.study.carDealershipsServer.domain.rental.dto.RentalOfferResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RentalOfferManagerService implements RentalOfferManagerFacade {

    @Override
    public void createRentalOffer(CreateRentalOfferRequest request, UUID managerId) {

    }

    @Override
    public Page<RentalOfferResource> getRentalOffers(Pageable pageable) {
        return null;
    }

    @Override
    public RentalOfferResource getRentalOffer(String vin) {
        return null;
    }

    @Override
    public void deleteRentalOffer(String vin) {

    }
}
