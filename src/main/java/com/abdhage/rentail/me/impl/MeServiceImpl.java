package com.abdhage.rentail.me.impl;

import com.abdhage.rentail.user.UserMapper;
import com.abdhage.rentail.user.UserResponse;
import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.me.MeService;
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
