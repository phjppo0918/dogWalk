package com.dogwalk.server.domain.auth;

import com.dogwalk.server.config.security.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody @Valid LoginRequest dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
