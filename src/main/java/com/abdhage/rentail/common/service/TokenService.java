package com.abdhage.rentail.common.service;

import com.abdhage.rentail.features.token.model.Token;

public interface TokenService {
    public void validateTokenExpires(Token token);
}
