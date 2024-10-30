package com.study.carDealershipsServer.domain.client.repository;

import com.study.carDealershipsServer.domain.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

}
