package com.study.carDealershipsServer.domain.auth.dto;

public record RegisterRequest(
        String firstName,
        String secondName,
        String lastName,
        String phone,
        String email,
        String personalNumber
) {

}
