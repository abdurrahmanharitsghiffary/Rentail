package com.abdhage.rentail.auth.dto;

import ch.usi.si.seart.validation.constraints.Password;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInDto {
    @NotEmpty(message = "Email is required")
    @Email
    @Max(50)
    private String email;

    @Password(minLength = 8, maxLength = 40, requirements = {Password.Requirement.NUMBER, Password.Requirement.SYMBOL, Password.Requirement.LOWERCASE_LETTER, Password.Requirement.UPPERCASE_LETTER})
    private String password;
}
