package com.dogwalk.server.config.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** jwt토큰을 통한 인가 필터 매 요청시 마다 호출 @Author Hyeonjun Park */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final String accessTokenHeaderTag;
    private final JwtValidator jwtValidator;

    public JwtAuthenticationFilter(
            @Value("${jwt.access-header}") String accessTokenHeaderTag, JwtValidator jwtValidator) {
        this.accessTokenHeaderTag = accessTokenHeaderTag;
        this.jwtValidator = jwtValidator;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 1. Request Header 에서 JWT 토큰 추출
        String token = resolveToken(request);

        // 2. validateToken 으로 토큰 유효성 검사
        if (token != null && jwtValidator.validateToken(token)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
            Authentication authentication = jwtValidator.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(accessTokenHeaderTag);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
