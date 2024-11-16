package com.study.carDealershipsServer.common.config.security;

import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

@Configuration
@Getter
public class JWTConfig {

    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    @Value("${security.jwt.auth_header:Authorization}")
    private String authHeader;
    @Value("${security.jwt.bearer_prefix:Bearer}")
    private String tokenPrefix;
    @Value("${security.jwt.expiration:#{3*60*60*1000}}")
    private long expirationTime;
}
