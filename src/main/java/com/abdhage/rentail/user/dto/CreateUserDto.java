package com.abdhage.rentail.user.dto;

import ch.usi.si.seart.validation.constraints.Password;
import com.abdhage.rentail.common.validation.Username;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotEmpty(message = "Email is required")
    @Email
    @Size(max = 50)
    private String email;

    @Password(minLength = 8, maxLength = 40, requirements = {Password.Requirement.NUMBER, Password.Requirement.SYMBOL, Password.Requirement.LOWERCASE_LETTER, Password.Requirement.UPPERCASE_LETTER})
    private String password;

    @NotEmpty(message = "displayName is required")
    @Size(min = 2)
    private String displayName;

    @NotEmpty(message = "username is required")
    @Username
    private String username;
}
