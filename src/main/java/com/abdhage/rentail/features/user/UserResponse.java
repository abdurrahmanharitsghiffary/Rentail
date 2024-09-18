package com.abdhage.rentail.features.user;

import com.abdhage.rentail.features.user.model.UserRole;
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
