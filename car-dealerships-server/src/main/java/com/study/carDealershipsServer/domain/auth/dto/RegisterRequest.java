package com.study.carDealershipsServer.domain.auth.dto;

import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
        String username,
        String password,
        String firstName,
        String secondName,
        String surname,
        String phone,
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        String email,
        String personalNumber
) {

}
