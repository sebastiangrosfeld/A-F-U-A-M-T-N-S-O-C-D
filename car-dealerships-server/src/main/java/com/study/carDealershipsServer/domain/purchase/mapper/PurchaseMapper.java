package com.study.carDealershipsServer.domain.purchase.mapper;

import com.study.carDealershipsServer.common.enums.PurchaseStatus;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.client.mapper.ClientMapper;
import com.study.carDealershipsServer.domain.client.repository.ClientRepository;
import com.study.carDealershipsServer.domain.manager.mapper.ManagerMapper;
import com.study.carDealershipsServer.domain.purchase.dto.CreatePurchaseRequest;
import com.study.carDealershipsServer.domain.purchase.dto.PurchaseResource;
import com.study.carDealershipsServer.domain.manager.entity.Manager;
import com.study.carDealershipsServer.domain.purchase.entity.Purchase;
import com.study.carDealershipsServer.domain.vehicle.entity.Vehicle;
import com.study.carDealershipsServer.domain.vehicle.mapper.VehicleMapper;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseMapper {

    private final EntityManager entityManager;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final ManagerMapper managerMapper;
    private final ClientMapper clientMapper;

    public Purchase createRequestToPurchase(CreatePurchaseRequest createPurchaseRequest) {
        Client clientProxy = entityManager.getReference(Client.class, clientRepository.getIdByEmail(createPurchaseRequest.email()));
        Manager managerProxy = entityManager.getReference(Manager.class, createPurchaseRequest.managerId());
        Vehicle vehicleProxy = entityManager.getReference(Vehicle.class, vehicleRepository.getIdByVinNumber(createPurchaseRequest.vinNumber()));
        return Purchase.builder()
                .purchaseId(UUID.randomUUID())
                .purchaseDate(LocalDateTime.now())
                .purchaseStatus(PurchaseStatus.ACTIVE)
                .client(clientProxy)
                .price(createPurchaseRequest.price())
                .manager(managerProxy)
                .vehicle(vehicleProxy).
                build();
    }

    public PurchaseResource purchaseToResource(Purchase purchase) {
        return PurchaseResource.builder()
                .purchaseId(purchase.getPurchaseId())
                .purchaseDate(purchase.getPurchaseDate())
                .status(purchase.getPurchaseStatus())
                .vehicleResource(vehicleMapper.entityToResource(purchase.getVehicle()))
                .managerResource(managerMapper.entityToResource(purchase.getManager()))
                .clientResource(clientMapper.entityToResource(purchase.getClient()))
                .build();
    }
}
