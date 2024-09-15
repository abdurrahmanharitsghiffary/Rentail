package com.goshier.kopos.service.impl;

import com.goshier.kopos.dtos.auth.SignInDto;
import com.goshier.kopos.dtos.auth.SignUpDto;
import com.goshier.kopos.exception.common.UnauthorizedException;
import com.goshier.kopos.model.Token;
import com.goshier.kopos.enums.TokenType;
import com.goshier.kopos.exception.InvalidCredentialsException;
import com.goshier.kopos.exception.UserAlreadyExistsException;
import com.goshier.kopos.repository.TokenRepository;
import com.goshier.kopos.model.User;
import com.goshier.kopos.repository.UserRepository;
import com.goshier.kopos.enums.UserRole;
import com.goshier.kopos.service.JwtService;
import com.goshier.kopos.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService, UserRepository userRepository, TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public Token signIn(SignInDto signInDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.getEmail(),
                        signInDto.getPassword()
                )
        );

        final User user = userRepository.findByEmail(signInDto.getEmail()).orElseThrow(InvalidCredentialsException::new);

        final String jwtToken = jwtService.generateToken(user);
        final Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .type(TokenType.ACCESS_TOKEN)
                .build();

        return tokenRepository.save(token);
    }

    @Transactional
    public Token signUp(SignUpDto signUpDto) {
        userRepository.findTopByUsernameOrEmail(signUpDto.getUsername(), signUpDto.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        final User createdUser = User
                .builder()
                .email(signUpDto.getEmail())
                .role(UserRole.MEMBER)
                .displayName(signUpDto.getDisplayName())
                .password(bCryptPasswordEncoder.encode(signUpDto.getPassword()))
                .username(signUpDto.getUsername())
                .build();

        userRepository.save(createdUser);
        final String generatedToken = jwtService.generateToken(createdUser);
        final Token token = Token.builder()
                .user(createdUser)
                .token(generatedToken)
                .type(TokenType.ACCESS_TOKEN)
                .build();

        return tokenRepository.save(token);
    }

    @Override
    public User getLoggedUserInformations() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        throw new UnauthorizedException("You are unauthenticated");
    }
}
