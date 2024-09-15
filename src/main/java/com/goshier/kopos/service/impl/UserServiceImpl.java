package com.goshier.kopos.service.impl;

import com.goshier.kopos.dtos.user.CreateUserDto;
import com.goshier.kopos.dtos.user.UpdateUserDto;
import com.goshier.kopos.enums.UserRole;
import com.goshier.kopos.mapper.UserMapper;
import com.goshier.kopos.model.Profile;
import com.goshier.kopos.model.User;
import com.goshier.kopos.response.UserResponse;
import com.goshier.kopos.repository.UserRepository;
import com.goshier.kopos.service.UserService;
import com.goshier.kopos.service.checker.UserChecker;
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

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserChecker userChecker) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userChecker = userChecker;
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(this.userMapper::userToUserResponse).toList();
    }

    @Transactional
    public UserResponse create(CreateUserDto dto) {
        userChecker.checkUserMustUnique(dto.getUsername(), dto.getEmail());

        final User user = userMapper.createUserDtoToUser(dto);
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.MEMBER);

        final Profile profile = Profile.builder().user(user).build();
        user.setProfile(profile);

        final User createdUser = userRepository.save(user);
        return userMapper.userToUserResponse(createdUser);
    }

    @Transactional
    public UserResponse update(UUID id, UpdateUserDto dto) {
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

    @Transactional
    public void destroy(UUID id) {
        userChecker.checkUserMustExists(id);
        userRepository.deleteById(id);
    }

    public UserResponse findOne(UUID id) {
        return userMapper.userToUserResponse(userChecker.checkUserMustExists(id));
    }

}
