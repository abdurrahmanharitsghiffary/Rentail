package com.abdhage.rentail.features.verifyaccount;

import com.abdhage.rentail.common.response.ApiResponse;
import com.abdhage.rentail.features.verifyaccount.dto.RequestVerifyAccountDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/verify-account")
public class VerifyAccountController {

    private final VerifyAccountService verifyAccountService;

    public VerifyAccountController(VerifyAccountService verifyAccountService) {
        this.verifyAccountService = verifyAccountService;
    }

    @PostMapping
    public ApiResponse<?> requestVerifyAccount(@Valid @RequestBody RequestVerifyAccountDTO dto) {
        verifyAccountService.requestVerifyAccount(dto.getEmail());
        return new ApiResponse<>(null, HttpStatus.OK.value(), "If your email is valid and correct link to verify your account will be sent to your email");
    }

    @PostMapping("{token}")
    public ApiResponse<?> verifyAccount(@PathVariable String token) {
        verifyAccountService.verifyAccount(token);
        return new ApiResponse<>(null, HttpStatus.OK.value(), "Account successfully verified");
    }
}
