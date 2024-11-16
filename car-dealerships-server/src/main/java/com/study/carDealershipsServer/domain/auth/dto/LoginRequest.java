package com.study.carDealershipsServer.domain.auth.dto;

import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        String email,
        String password,
        String verificationCode
) {
}
