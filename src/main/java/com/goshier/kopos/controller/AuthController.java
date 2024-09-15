package com.goshier.kopos.controller;

import com.goshier.kopos.mapper.TokenMapper;
import com.goshier.kopos.model.Token;
import com.goshier.kopos.service.AuthService;
import com.goshier.kopos.dtos.auth.SignInDto;
import com.goshier.kopos.dtos.auth.SignUpDto;
import com.goshier.kopos.response.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final private AuthService authService;
    private final TokenMapper tokenMapper;

    public AuthController(AuthService authService,
                          TokenMapper tokenMapper) {
        this.authService = authService;
        this.tokenMapper = tokenMapper;
    }

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody(required = true) SignInDto signInDto) {
        final Token token = authService.signIn(signInDto);
        return tokenMapper.tokenToTokenResponse(token);
    }

    @PostMapping("/sign-up")
    public TokenResponse signUp(@RequestBody(required = true) SignUpDto signUpDto) {
        final Token token = authService.signUp(signUpDto);
        return tokenMapper.tokenToTokenResponse(token);
    }
}
