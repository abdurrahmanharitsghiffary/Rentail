package com.abdhage.rentail.dtos.auth;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDto {
    @NotEmpty(message = "Email is required")
    @Email
    @Max(50)
    private String email;

    @NotEmpty(message = "Password is required")
    @Min(8)
    @Max(50)
    private String password;

    @NotEmpty(message = "displayName is required")
    @Min(2)
    private String displayName;

    @NotEmpty(message = "username is required")
    @Min(2)
    @Max(150)
    private String username;
}
