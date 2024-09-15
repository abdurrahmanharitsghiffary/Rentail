package com.abdhage.rentail.service.impl;

import com.abdhage.rentail.mapper.UserMapper;
import com.abdhage.rentail.response.UserResponse;
import com.abdhage.rentail.service.AuthService;
import com.abdhage.rentail.service.MeService;
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
