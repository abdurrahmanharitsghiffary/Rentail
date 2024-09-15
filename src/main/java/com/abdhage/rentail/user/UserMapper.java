package com.abdhage.rentail.user;

import com.abdhage.rentail.user.dto.CreateUserDto;
import com.abdhage.rentail.user.dto.UpdateUserDto;
import com.abdhage.rentail.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", expression = "java(user.getRealUsername())")
    public UserResponse userToUserResponse(User user);

    public User userResponseToUser(UserResponse userResponse);

    public User createUserDtoToUser(CreateUserDto createUserDto);

    public User updateUserDtoToUser(UpdateUserDto updateUserDto);

    public List<UUID> usersToUserIds(List<User> users);

    public UUID userToUserId(User user);
}
