package com.study.carDealershipsServer.application.authentication.controller;

import com.study.carDealershipsServer.application.authentication.useCase.AuthInterface;
import com.study.carDealershipsServer.domain.auth.dto.ChangePasswordRequest;
import com.study.carDealershipsServer.domain.auth.dto.LoginRequest;
import com.study.carDealershipsServer.domain.auth.dto.RegisterRequest;
import com.study.carDealershipsServer.domain.auth.dto.TokenResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(AUTH_PREFIX)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthInterface authInterface;

    @PostMapping("/login")
    public ResponseEntity<TokenResource> login(LoginRequest loginRequest) {
        var token = authInterface.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(RegisterRequest registerRequest) {
        authInterface.register(registerRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("change-password")
    public ResponseEntity<Void> changePassword(ChangePasswordRequest changePasswordRequest) {
        authInterface.changePassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }
}
