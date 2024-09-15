package com.abdhage.rentail.user.dto;

import com.abdhage.rentail.common.validation.Password;
import com.abdhage.rentail.common.validation.Username;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateUserDto {
    @Email
    @Size(max = 50)
    private String email;

    @Password
    private String password;

    @Size(min = 2)
    private String displayName;

    @Username
    private String username;
}
