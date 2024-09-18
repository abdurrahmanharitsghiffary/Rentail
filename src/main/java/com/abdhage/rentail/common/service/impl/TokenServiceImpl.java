package com.abdhage.rentail.common.service.impl;

import com.abdhage.rentail.common.exception.BadRequestException;
import com.abdhage.rentail.common.service.TokenService;
import com.abdhage.rentail.features.token.TokenRepository;
import com.abdhage.rentail.features.token.model.Token;
import com.abdhage.rentail.features.token.model.TokenType;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void validateTokenExpires(Token token) {
        if (token.getType() != TokenType.ACCESS_TOKEN) {
            Long tokenCreatedTime = token.getCreatedAt().getTime();
            Long expiresAt = token.getExpiresAt();

            if (expiresAt == null) {
                expiresAt = 3600L;
            }

            if ((tokenCreatedTime + expiresAt) > System.currentTimeMillis()) {
                tokenRepository.delete(token);
                throw new BadRequestException("Token already expired");
            }
        }
    }
}
