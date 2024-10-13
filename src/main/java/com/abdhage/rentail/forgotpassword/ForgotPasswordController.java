package com.abdhage.rentail.forgotpassword;

import com.abdhage.rentail.forgotpassword.dto.ForgotPasswordRequest;
import com.abdhage.rentail.forgotpassword.dto.ResetPasswordRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/forgot-password")
    public String requestResetPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        forgotPasswordService.requestResetPassword(request);
        return "If your email is valid and found, url to reset your password will be sent to your email";
    }

    @PostMapping("/reset-password/{token}")
    public String resetPassword(@PathVariable String token, @Valid @RequestBody ResetPasswordRequest request) {
        forgotPasswordService.resetPassword(token, request);
        return "Password successfully reseted";
    }
}
