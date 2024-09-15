package com.abdhage.rentail.mapper;

import com.abdhage.rentail.dtos.user.CreateUserDto;
import com.abdhage.rentail.dtos.user.UpdateUserDto;
import com.abdhage.rentail.model.User;
import com.abdhage.rentail.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {

    @Mapping(target = "username", expression = "java(user.getRealUsername())")
    public UserResponse userToUserResponse(User user);

    public User userResponseToUser(UserResponse userResponse);

    public User createUserDtoToUser(CreateUserDto createUserDto);

    public User updateUserDtoToUser(UpdateUserDto updateUserDto);

    public List<UUID> usersToUserIds(List<User> users);

    public UUID userToUserId(User user);
}
