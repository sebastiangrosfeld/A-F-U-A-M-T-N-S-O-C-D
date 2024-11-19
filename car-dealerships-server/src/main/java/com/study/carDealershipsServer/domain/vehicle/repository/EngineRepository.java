package com.study.carDealershipsServer.domain.vehicle.repository;

import com.study.carDealershipsServer.domain.vehicle.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

    boolean existsByName(String name);

    Optional<Engine> findByName(String name);

    List<Engine> findAllByNameIn(List<String> names);
}
