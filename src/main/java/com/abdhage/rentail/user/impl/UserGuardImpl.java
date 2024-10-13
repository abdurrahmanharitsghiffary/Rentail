package com.abdhage.rentail.user.impl;

import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.common.exception.ForbiddenException;
import com.abdhage.rentail.user.UserGuard;
import com.abdhage.rentail.user.model.UserRole;
import org.springframework.stereotype.Service;

@Service
public class UserGuardImpl implements UserGuard {

    private final AuthService authService;

    public UserGuardImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void verifyPermission() {
        UserRole role = authService.getLoggedUserInformations().getRole();

        if (role != UserRole.ADMIN) {
            throw new ForbiddenException();
        }
    }
}
