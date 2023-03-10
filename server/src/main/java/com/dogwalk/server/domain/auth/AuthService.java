package com.dogwalk.server.domain.auth;

import com.dogwalk.server.config.security.jwt.JwtProvider;
import com.dogwalk.server.config.security.jwt.JwtToken;
import com.dogwalk.server.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements LoginUserLoader {
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    public JwtToken login(LoginRequest dto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);
        return jwtProvider.createJWTTokens(authentication);
    }

    @Override
    public Member getLoginUser() {
        return (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
