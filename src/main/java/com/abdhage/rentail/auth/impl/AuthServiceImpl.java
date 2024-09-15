package com.abdhage.rentail.auth.impl;

import com.abdhage.rentail.auth.dto.SignInDto;
import com.abdhage.rentail.auth.dto.SignUpDto;
import com.abdhage.rentail.common.exception.UnauthorizedException;
import com.abdhage.rentail.token.model.Token;
import com.abdhage.rentail.token.model.TokenType;
import com.abdhage.rentail.auth.exception.InvalidCredentialsException;
import com.abdhage.rentail.user.exception.UserAlreadyExistsException;
import com.abdhage.rentail.token.TokenRepository;
import com.abdhage.rentail.user.model.User;
import com.abdhage.rentail.user.UserRepository;
import com.abdhage.rentail.user.model.UserRole;
import com.abdhage.rentail.common.service.JwtService;
import com.abdhage.rentail.auth.AuthService;
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
