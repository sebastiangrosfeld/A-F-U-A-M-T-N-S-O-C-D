package com.study.carDealershipsServer.domain.client.repository;

import com.study.carDealershipsServer.domain.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository <Client, UUID> {

    @Query("SELECT c.id FROM Client c WHERE c.email = :email")
    UUID getIdByEmail(String email);

}
