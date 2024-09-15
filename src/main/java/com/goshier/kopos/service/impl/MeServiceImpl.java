package com.goshier.kopos.service.impl;

import com.goshier.kopos.mapper.UserMapper;
import com.goshier.kopos.response.UserResponse;
import com.goshier.kopos.service.AuthService;
import com.goshier.kopos.service.MeService;
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
