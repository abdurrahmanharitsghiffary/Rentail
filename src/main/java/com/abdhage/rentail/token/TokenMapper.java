package com.abdhage.rentail.token;

import com.abdhage.rentail.token.model.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    public TokenResponse tokenToTokenResponse(Token token);

}
