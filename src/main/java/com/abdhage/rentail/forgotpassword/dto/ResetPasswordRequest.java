package com.abdhage.rentail.forgotpassword.dto;

import ch.usi.si.seart.validation.constraints.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequest {
    @Password(minLength = 8, maxLength = 40, requirements = {Password.Requirement.NUMBER, Password.Requirement.SYMBOL, Password.Requirement.LOWERCASE_LETTER, Password.Requirement.UPPERCASE_LETTER})
    private String newPassword;

    @Password(minLength = 8, maxLength = 40, requirements = {Password.Requirement.NUMBER, Password.Requirement.SYMBOL, Password.Requirement.LOWERCASE_LETTER, Password.Requirement.UPPERCASE_LETTER})
    private String confirmPassword;
}
