package com.abdhage.rentail.token;

import com.abdhage.rentail.token.model.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {
    public String token;
    public TokenType type;
}
