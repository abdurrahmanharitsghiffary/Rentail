package com.abdhage.rentail.user.impl;

import com.abdhage.rentail.user.*;
import com.abdhage.rentail.user.dto.CreateUserDto;
import com.abdhage.rentail.user.dto.UpdateUserDto;
import com.abdhage.rentail.user.model.Profile;
import com.abdhage.rentail.user.model.User;
import com.abdhage.rentail.user.model.UserRole;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserChecker userChecker;
    private final UserGuard userGuard;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserChecker userChecker, UserGuard userGuard) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userChecker = userChecker;
        this.userGuard = userGuard;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(this.userMapper::userToUserResponse)
                .toList();
    }

    @Override
    @Transactional
    public UserResponse create(CreateUserDto dto) {
        userGuard.verifyPermission();
        userChecker.checkUserMustUnique(dto.getUsername(), dto.getEmail());

        final User user = userMapper.createUserDtoToUser(dto);
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.MEMBER);

        final Profile profile = Profile
                .builder()
                .user(user)
                .build();

        user.setProfile(profile);

        final User createdUser = userRepository.save(user);
        return userMapper.userToUserResponse(createdUser);
    }

    @Override
    @Transactional
    public UserResponse update(UUID id, UpdateUserDto dto) {
        userGuard.verifyPermission();
        final User theUser = userChecker.checkUserMustExists(id);

        if (!theUser.getRealUsername().equals(dto.getUsername()))
            userChecker.checkUsernameMustUnique(dto.getUsername());

        if (!theUser.getEmail().equals(dto.getEmail())) userChecker.checkEmailMustUnique(dto.getEmail());

        if (dto.getEmail() != null) theUser.setEmail(dto.getEmail());
        if (dto.getDisplayName() != null) theUser.setDisplayName(dto.getDisplayName());
        if (dto.getUsername() != null) theUser.setUsername(dto.getUsername());
        if (dto.getPassword() != null) theUser.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        final User updatedUser = userRepository.save(theUser);
        return userMapper.userToUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public void destroy(UUID id) {
        userGuard.verifyPermission();
        userChecker.checkUserMustExists(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findOne(UUID id) {
        return userMapper
                .userToUserResponse(userChecker.checkUserMustExists(id));
    }

}
