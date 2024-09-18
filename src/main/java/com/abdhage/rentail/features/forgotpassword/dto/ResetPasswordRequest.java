package com.abdhage.rentail.features.forgotpassword.dto;

import com.abdhage.rentail.common.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequest {
    @Password
    private String newPassword;
    @Password
    private String confirmPassword;
}
