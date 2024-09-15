package com.abdhage.rentail.controller;

import com.abdhage.rentail.mapper.TokenMapper;
import com.abdhage.rentail.model.Token;
import com.abdhage.rentail.service.AuthService;
import com.abdhage.rentail.dtos.auth.SignInDto;
import com.abdhage.rentail.dtos.auth.SignUpDto;
import com.abdhage.rentail.response.TokenResponse;
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
