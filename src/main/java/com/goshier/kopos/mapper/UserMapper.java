package com.goshier.kopos.mapper;

import com.goshier.kopos.dtos.user.CreateUserDto;
import com.goshier.kopos.dtos.user.UpdateUserDto;
import com.goshier.kopos.model.User;
import com.goshier.kopos.response.UserResponse;
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
