package com.study.carDealershipsServer.domain.manager.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record ManagerResource(
        String name,
        String surname,
        String branchOffice
) {
}
