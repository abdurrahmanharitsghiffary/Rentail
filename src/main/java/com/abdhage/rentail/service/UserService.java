package com.abdhage.rentail.service;

import com.abdhage.rentail.dtos.user.CreateUserDto;
import com.abdhage.rentail.dtos.user.UpdateUserDto;
import com.abdhage.rentail.response.UserResponse;
import com.abdhage.rentail.service.common.BaseCrudService;

import java.util.UUID;

public interface UserService extends BaseCrudService<UserResponse, CreateUserDto, UpdateUserDto, UUID> {
}
