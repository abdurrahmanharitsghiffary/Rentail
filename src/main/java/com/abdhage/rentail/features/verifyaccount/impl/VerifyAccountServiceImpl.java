package com.abdhage.rentail.features.verifyaccount.impl;

import com.abdhage.rentail.common.dto.EmailDetailsDTO;
import com.abdhage.rentail.common.exception.BadRequestException;
import com.abdhage.rentail.common.service.MailService;
import com.abdhage.rentail.common.service.TokenService;
import com.abdhage.rentail.features.token.TokenRepository;
import com.abdhage.rentail.features.token.model.Token;
import com.abdhage.rentail.features.token.model.TokenType;
import com.abdhage.rentail.features.user.UserRepository;
import com.abdhage.rentail.features.user.model.User;
import com.abdhage.rentail.features.verifyaccount.VerifyAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VerifyAccountServiceImpl implements VerifyAccountService {

    private final TokenService tokenService;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public VerifyAccountServiceImpl(TokenService tokenService, MailService mailService, UserRepository userRepository,
                                    TokenRepository tokenRepository) {
        this.tokenService = tokenService;
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void requestVerifyAccount(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            Token token = Token.builder()
                    .token(UUID.randomUUID().toString())
                    .type(TokenType.VERIFY_TOKEN)
                    .expiresAt(3600L)
                    .user(user)
                    .build();

            mailService.sendMail(new EmailDetailsDTO("Verify Account", "Visit here to verify your account: http://localhost:8080/api/v1/auth/verify-account/" + token.getToken(), email));
            tokenRepository.save(token);
        });
    }

    @Override
    public void verifyAccount(String token) {
        Optional<Token> verifyToken = tokenRepository.findByToken(token);

        verifyToken.ifPresent(t -> {
            tokenService.validateTokenExpires(t);

            User user = t.getUser();
            user.setIsVerified(true);
            tokenRepository.deleteByTypeAndUser(TokenType.VERIFY_TOKEN, user);
        });

        verifyToken.orElseThrow(() -> new BadRequestException("Invalid Token."));
    }
}
