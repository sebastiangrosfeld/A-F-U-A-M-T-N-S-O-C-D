package com.study.carDealershipsServer.domain.manager.mapper;

import com.study.carDealershipsServer.domain.manager.dto.ManagerResource;
import com.study.carDealershipsServer.domain.manager.entity.Manager;
import org.springframework.stereotype.Service;

@Service
public class ManagerMapper {

    public ManagerResource entityToResource(Manager manager) {
        return ManagerResource.builder()
                .name(manager.getName())
                .surname(manager.getSurname())
                .branchOffice(manager.getBranchOffice())
                .build();
    }
}
