package com.abdhage.rentail.response;

import com.abdhage.rentail.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String email;
    private UserRole role;
    private String displayName;
    private String username;
    private Date createdAt;
    private Date updatedAt;
}
