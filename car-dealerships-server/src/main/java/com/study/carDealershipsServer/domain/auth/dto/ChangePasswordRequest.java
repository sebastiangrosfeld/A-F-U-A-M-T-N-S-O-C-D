package com.study.carDealershipsServer.domain.auth.dto;

public record ChangePasswordRequest (
        String email,
        String oldPassword,
        String newPassword
) {
}
