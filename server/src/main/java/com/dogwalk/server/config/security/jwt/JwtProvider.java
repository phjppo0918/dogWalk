package com.dogwalk.server.config.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * jwt token을 생성
 *
 * @author Hyeonjun Park
 */
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final Key key;

    private static final Long ACCESS_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 1000; // 1 Day
    private static final Long REFRESH_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 14 * 1000; // 14 Day

    public JwtToken createJWTTokens(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_VALIDATION_SECOND);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_VALIDATION_SECOND))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
