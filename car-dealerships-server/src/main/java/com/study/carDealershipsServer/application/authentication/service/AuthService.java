package com.study.carDealershipsServer.application.authentication.service;

import com.study.carDealershipsServer.application.authentication.useCase.AuthInterface;
import com.study.carDealershipsServer.common.config.security.JWTConfig;
import com.study.carDealershipsServer.common.config.service.EmailService;
import com.study.carDealershipsServer.common.config.service.QRService;
import com.study.carDealershipsServer.common.enums.Roles;
import com.study.carDealershipsServer.common.errors.ServiceException;
import com.study.carDealershipsServer.domain.auth.repository.AppUserRepository;
import com.study.carDealershipsServer.domain.auth.dto.ChangePasswordRequest;
import com.study.carDealershipsServer.domain.auth.dto.LoginRequest;
import com.study.carDealershipsServer.domain.auth.dto.RegisterRequest;
import com.study.carDealershipsServer.domain.auth.dto.TokenResource;
import com.study.carDealershipsServer.domain.auth.entity.AppUser;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.client.repository.ClientRepository;
import com.study.carDealershipsServer.domain.manager.repository.ManagerRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthInterface, UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final JWTConfig jwtConfig;
    private final QRService qrService;
    private final EmailService emailService;

    @Value("${security.jwt.authorities_prefix:authorities}")
    private String authorities;

    @Override
    public void register(RegisterRequest registerRequest) {
        if (appUserRepository.existsByUsername(registerRequest.username()) || appUserRepository.existsByEmail(registerRequest.email())) {
            throw new ServiceException("Bad register credentials", HttpStatus.BAD_REQUEST);
        }
        if (clientRepository.existsByEmail(registerRequest.email()) || appUserRepository.existsByEmail(registerRequest.email())) {
            throw new ServiceException("Bad register credentials", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.password());

        var appUser = AppUser.builder()
                .userId(UUID.randomUUID())
                .username(registerRequest.username())
                .password(encodedPassword)
                .role(Roles.ROLE_CLIENT)
                .email(registerRequest.email())
                .totpSecret(Base32.random())
                .build();

        if (clientRepository.existsById(appUser.getUserId()) || clientRepository.existsByPersonalNumber(registerRequest.personalNumber())) {
            throw new ServiceException("Bad register credentials", HttpStatus.BAD_REQUEST);
        }

        var client = Client.builder()
                .active(true)
                .email(registerRequest.email())
                .phone(registerRequest.phone())
                .firstName(registerRequest.firstName())
                .secondName(registerRequest.secondName())
                .personalNumber(registerRequest.personalNumber())
                .surname(registerRequest.surname())
                .build();

        var qr = qrService.generateQRUrl(appUser);

        String emailText = "One time password qr code: " + qr + " \n" +
                "One time password secret code: " + appUser.getTotpSecret() + " \n" +
                "For security delete this mail after configuration your OTP application. \n"
                 + "This mail was generated automatically. Please not respond for this. \n";

        emailService.sendMail("Your OTP credentials for CarDealershipApp.", emailText, appUser.getEmail());

        appUserRepository.save(appUser);
        clientRepository.save(client);
    }

    @Override
    public TokenResource login(LoginRequest loginRequest) {
        String encodedPassword = passwordEncoder.encode(loginRequest.password());
        AppUser appUser = appUserRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new ServiceException("Bad login credentials", HttpStatus.UNAUTHORIZED));
        if (!encodedPassword.equals(appUser.getPassword())) {
            throw new ServiceException("Bad login credentials", HttpStatus.UNAUTHORIZED);
        }
        Totp totp = new Totp(appUser.getTotpSecret());

        if (!isValidLong(loginRequest.verificationCode()) || !totp.verify(loginRequest.verificationCode())) {
            throw new ServiceException("Bad login credentials", HttpStatus.UNAUTHORIZED);
        }

        String jwt = Jwts.builder()
                .subject(appUser.getUsername())
                .claim(authorities, appUser.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .signWith(jwtConfig.getSecretKey())
                .compact();

        return new TokenResource(jwt);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        AppUser appUser = appUserRepository.findByEmail(changePasswordRequest.email())
                .orElseThrow(() -> new ServiceException("Bad change password credentials", HttpStatus.UNAUTHORIZED));

        if (!changePasswordRequest.oldPassword().equals(appUser.getPassword())) {
            throw new ServiceException("Bad change password credentials", HttpStatus.UNAUTHORIZED);
        }
        String encodedPassword = passwordEncoder.encode(changePasswordRequest.newPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not founded" + username));
    }

    public static UUID getAuthUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AppUser appUser) {
            return appUser.getUserId();
        }
        return null;
    }

    public static void checkUserRole(Roles role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AppUser appUser) {
            if(!appUser.getRole().equals(role)) {
                System.out.println(appUser.getRole());
                System.out.println(role);
                throw new ServiceException("Bad user role. " + " " + appUser.getRole() + " " + role, HttpStatus.UNAUTHORIZED);
            }
        } else {
          throw new ServiceException("Not founded user role.", HttpStatus.UNAUTHORIZED);
        }

    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
