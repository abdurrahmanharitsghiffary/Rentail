package com.goshier.kopos.service;

import com.goshier.kopos.dtos.user.CreateUserDto;
import com.goshier.kopos.dtos.user.UpdateUserDto;
import com.goshier.kopos.response.UserResponse;
import com.goshier.kopos.service.common.BaseCrudService;

import java.util.UUID;

public interface UserService extends BaseCrudService<UserResponse, CreateUserDto, UpdateUserDto, UUID> {
}
