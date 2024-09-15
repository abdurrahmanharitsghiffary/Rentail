package com.abdhage.rentail.dtos.user;

import com.abdhage.rentail.validation.Password;
import com.abdhage.rentail.validation.Username;
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
