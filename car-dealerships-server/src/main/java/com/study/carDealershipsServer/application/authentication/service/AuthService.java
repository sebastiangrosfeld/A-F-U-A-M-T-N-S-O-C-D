package com.study.carDealershipsServer.application.authentication.service;

import com.study.carDealershipsServer.application.authentication.useCase.AuthInterface;
import com.study.carDealershipsServer.domain.auth.dto.LoginRequest;
import com.study.carDealershipsServer.domain.auth.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthInterface {
    @Override
    public void register(RegisterRequest registerRequest) {

    }

    @Override
    public void login(LoginRequest loginRequest) {

    }

    @Override
    public void changePassword() {

    }
}
