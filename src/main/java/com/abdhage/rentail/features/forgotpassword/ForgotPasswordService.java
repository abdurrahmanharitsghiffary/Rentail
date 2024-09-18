package com.abdhage.rentail.features.forgotpassword;

import com.abdhage.rentail.features.forgotpassword.dto.ForgotPasswordRequest;
import com.abdhage.rentail.features.forgotpassword.dto.ResetPasswordRequest;

public interface ForgotPasswordService {
    public void requestResetPassword(ForgotPasswordRequest request);

    public void resetPassword(String token, ResetPasswordRequest request);
}
