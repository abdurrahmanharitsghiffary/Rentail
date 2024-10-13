package com.abdhage.rentail.user.dto;

import ch.usi.si.seart.validation.constraints.Password;
import com.abdhage.rentail.common.validation.Username;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateUserDto {
    @Email
    @Size(max = 50)
    private String email;

    @Password(minLength = 8, maxLength = 40, requirements = {Password.Requirement.NUMBER, Password.Requirement.SYMBOL, Password.Requirement.LOWERCASE_LETTER, Password.Requirement.UPPERCASE_LETTER})
    private String password;

    @Size(min = 2)
    private String displayName;

    @Username
    private String username;
}
