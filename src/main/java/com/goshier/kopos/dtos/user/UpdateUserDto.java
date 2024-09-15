package com.goshier.kopos.dtos.user;

import com.goshier.kopos.validation.Password;
import com.goshier.kopos.validation.Username;
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
