package com.abdhage.rentail.features.me.impl;

import com.abdhage.rentail.features.user.UserMapper;
import com.abdhage.rentail.features.user.UserResponse;
import com.abdhage.rentail.features.auth.AuthService;
import com.abdhage.rentail.features.me.MeService;
import org.springframework.stereotype.Component;

@Component
public class MeServiceImpl implements MeService {
    private final AuthService authService;
    private final UserMapper userMapper;

    public MeServiceImpl(AuthService authService,
                         UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse getMyInformations() {
        return userMapper.userToUserResponse(authService.getLoggedUserInformations());
    }
}
