package com.study.carDealershipsServer.domain.purchase.mapper;

import com.study.carDealershipsServer.domain.manager.mapper.ManagerMapper;
import com.study.carDealershipsServer.domain.purchase.dto.CreatePurchaseOfferRequest;
import com.study.carDealershipsServer.domain.purchase.dto.PurchaseOfferResource;
import com.study.carDealershipsServer.domain.manager.entity.Manager;
import com.study.carDealershipsServer.domain.purchase.entity.PurchaseOffer;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import com.study.carDealershipsServer.domain.vehicle.mapper.VehicleMapper;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOfferMapper {

    private  final VehicleRepository vehicleRepository;
    private final EntityManager entityManager;
    private final VehicleMapper vehicleMapper;
    private final ManagerMapper managerMapper;

    public PurchaseOffer createRequestToPurchaseOffer(CreatePurchaseOfferRequest  request, UUID managerId) {
        Manager managerProxy = entityManager.find(Manager.class, managerId);
        var vehicleId = vehicleRepository.getIdByVinNumber(request.vehicleVin());
        Vehicle vehicleProxy = entityManager.find(Vehicle.class, vehicleId);
        return PurchaseOffer.builder()
                .price(request.price())
                .manager(managerProxy)
                .vehicle(vehicleProxy)
                .build();
    }

    public PurchaseOfferResource entityToResource(PurchaseOffer purchaseOffer) {
        return PurchaseOfferResource.builder()
                .price(purchaseOffer.getPrice())
                .managerResource(managerMapper.entityToResource(purchaseOffer.getManager()))
                .vehicleResource(vehicleMapper.entityToResource(purchaseOffer.getVehicle()))
                .build();
    }
}
