package com.abdhage.rentail.mapper;

import com.abdhage.rentail.model.Token;
import com.abdhage.rentail.response.TokenResponse;
import org.mapstruct.Mapper;

@Mapper
public interface TokenMapper {

    public TokenResponse tokenToTokenResponse(Token token);

}
