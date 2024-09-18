package com.abdhage.rentail.features.token;

import com.abdhage.rentail.features.token.model.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    public TokenResponse tokenToTokenResponse(Token token);

}
