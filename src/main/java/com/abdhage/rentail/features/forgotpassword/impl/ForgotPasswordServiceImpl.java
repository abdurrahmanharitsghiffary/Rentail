package com.abdhage.rentail.features.forgotpassword.impl;

import com.abdhage.rentail.common.dto.EmailDetailsDTO;
import com.abdhage.rentail.common.exception.BadRequestException;
import com.abdhage.rentail.common.service.MailService;
import com.abdhage.rentail.common.service.TokenService;
import com.abdhage.rentail.features.forgotpassword.ForgotPasswordService;
import com.abdhage.rentail.features.forgotpassword.dto.ForgotPasswordRequest;
import com.abdhage.rentail.features.forgotpassword.dto.ResetPasswordRequest;
import com.abdhage.rentail.features.token.TokenRepository;
import com.abdhage.rentail.features.token.model.Token;
import com.abdhage.rentail.features.token.model.TokenType;
import com.abdhage.rentail.features.user.UserRepository;
import com.abdhage.rentail.features.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final TokenService tokenService;
    private static final Logger log = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final MailService mailService;

    public ForgotPasswordServiceImpl(TokenService tokenService, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository, UserRepository userRepository, MailService mailService) {
        this.tokenService = tokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public void requestResetPassword(ForgotPasswordRequest request) {

        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            log.info(user + "User");
            Token token = Token.builder()
                    .user(user)
                    .type(TokenType.RESET_TOKEN)
                    .token(UUID.randomUUID().toString())
                    .expiresAt(600L)
                    .build();

            tokenRepository.save(token);

            EmailDetailsDTO emailDetails = EmailDetailsDTO.builder()
                    .subject("Reset Password")
                    .body("Visit here to reset your password: " + "http://localhost:8080/api/v1/auth/reset-password/" + token.getToken() + "\nToken will be expired in 5 minutes")
                    .recipient(user.getEmail())
                    .build();

            mailService.sendMail(emailDetails);
        });

    }

    @Override
    public void resetPassword(String token, ResetPasswordRequest request) {
        Optional<Token> resetToken = tokenRepository.findByToken(token);
        resetToken.ifPresent((t) -> {
            tokenService.validateTokenExpires(t);

            User user = t.getUser();
            user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));

            userRepository.save(user);
            tokenRepository.deleteByTypeAndUser(TokenType.RESET_TOKEN, user);
        });
        resetToken.orElseThrow(() -> new BadRequestException("Invalid token"));
    }
}
