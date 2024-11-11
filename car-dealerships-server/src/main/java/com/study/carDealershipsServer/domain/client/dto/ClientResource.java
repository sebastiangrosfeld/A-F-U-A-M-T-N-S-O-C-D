package com.study.carDealershipsServer.domain.client.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record ClientResource(
        String firstName,
        String secondName,
        String surname,
        String phone
) {
}
