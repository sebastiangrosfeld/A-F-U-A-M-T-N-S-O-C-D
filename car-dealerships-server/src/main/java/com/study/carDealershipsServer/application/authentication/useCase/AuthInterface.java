package com.study.carDealershipsServer.application.authentication.useCase;

import com.study.carDealershipsServer.domain.auth.dto.ChangePasswordRequest;
import com.study.carDealershipsServer.domain.auth.dto.LoginRequest;
import com.study.carDealershipsServer.domain.auth.dto.RegisterRequest;
import com.study.carDealershipsServer.domain.auth.dto.TokenResource;

public interface AuthInterface {

    void register(RegisterRequest registerRequest);

    TokenResource login(LoginRequest loginRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);
}
