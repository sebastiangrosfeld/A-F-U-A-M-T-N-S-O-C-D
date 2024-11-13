package com.study.carDealershipsServer.application.authentication.useCase;

import com.study.carDealershipsServer.domain.auth.dto.LoginRequest;
import com.study.carDealershipsServer.domain.auth.dto.RegisterRequest;

public interface AuthInterface {

    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    void changePassword();
}
