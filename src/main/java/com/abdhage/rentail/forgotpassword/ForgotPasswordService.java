package com.abdhage.rentail.forgotpassword;

import com.abdhage.rentail.forgotpassword.dto.ForgotPasswordRequest;
import com.abdhage.rentail.forgotpassword.dto.ResetPasswordRequest;

public interface ForgotPasswordService {
    public void requestResetPassword(ForgotPasswordRequest request);

    public void resetPassword(String token, ResetPasswordRequest request);
}
