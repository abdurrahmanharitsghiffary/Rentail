package com.goshier.kopos.dtos.user;

import com.goshier.kopos.validation.Password;
import com.goshier.kopos.validation.Username;
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
