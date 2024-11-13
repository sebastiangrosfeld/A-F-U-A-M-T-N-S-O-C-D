package com.study.carDealershipsServer.domain.client.mapper;

import com.study.carDealershipsServer.domain.client.dto.ClientResource;
import com.study.carDealershipsServer.domain.client.entity.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public ClientResource entityToResource(Client client) {
        return ClientResource.builder()
                .firstName(client.getFirstName())
                .surname(client.getSurname())
                .phone(client.getPhone())
                .secondName(client.getSecondName())
                .build();
    }
}
