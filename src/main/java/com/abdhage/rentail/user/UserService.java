package com.abdhage.rentail.user;

import com.abdhage.rentail.user.dto.CreateUserDto;
import com.abdhage.rentail.user.dto.UpdateUserDto;
import com.abdhage.rentail.common.service.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface UserService extends BaseCrudService<UserResponse, CreateUserDto, UpdateUserDto, UUID> {
}
