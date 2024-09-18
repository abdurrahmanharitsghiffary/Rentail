package com.abdhage.rentail.features.user.dto;

import com.abdhage.rentail.common.validation.Password;
import com.abdhage.rentail.common.validation.Username;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotEmpty(message = "Email is required")
    @Email
    @Size(max = 50)
    private String email;

    @NotEmpty(message = "Password is required")
    @Password
    private String password;

    @NotEmpty(message = "displayName is required")
    @Size(min = 2)
    private String displayName;

    @NotEmpty(message = "username is required")
    @Username
    private String username;
}
