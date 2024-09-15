package com.goshier.kopos.mapper;

import com.goshier.kopos.model.Token;
import com.goshier.kopos.response.TokenResponse;
import org.mapstruct.Mapper;

@Mapper
public interface TokenMapper {

    public TokenResponse tokenToTokenResponse(Token token);

}
