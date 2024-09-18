package com.abdhage.rentail.features.user;

import com.abdhage.rentail.features.user.dto.CreateUserDto;
import com.abdhage.rentail.features.user.dto.UpdateUserDto;
import com.abdhage.rentail.common.service.BaseCrudService;

import java.util.UUID;

public interface UserService extends BaseCrudService<UserResponse, CreateUserDto, UpdateUserDto, UUID> {
}
