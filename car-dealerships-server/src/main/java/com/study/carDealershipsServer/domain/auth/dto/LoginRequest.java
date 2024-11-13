package com.study.carDealershipsServer.domain.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
