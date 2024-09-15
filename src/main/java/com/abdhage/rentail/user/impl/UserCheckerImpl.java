package com.abdhage.rentail.user.impl;

import com.abdhage.rentail.auth.exception.EmailMustUniqueException;
import com.abdhage.rentail.user.exception.UserNotFoundException;
import com.abdhage.rentail.auth.exception.UsernameMustUniqueException;
import com.abdhage.rentail.common.exception.NotFoundException;
import com.abdhage.rentail.common.model.BaseEntity;
import com.abdhage.rentail.user.model.User;
import com.abdhage.rentail.user.UserRepository;
import com.abdhage.rentail.user.UserChecker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
public class UserCheckerImpl implements UserChecker {

    private final UserRepository userRepository;

    public UserCheckerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void checkUserMustUnique(String username, String email) {
        checkUsernameMustUnique(username);
        checkEmailMustUnique(email);
    }

    @Override
    public void checkUsernameMustUnique(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new UsernameMustUniqueException();
        });
    }

    @Override
    public void checkEmailMustUnique(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new EmailMustUniqueException();
        });
    }

    @Override
    public User checkUserMustExists(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> checkBatchUserMustExists(List<UUID> ids) {
        final HashSet<Object> errors = new HashSet<>();

        final List<User> users = userRepository.findAllById(ids);
        final List<UUID> userIds = usersToUserIds(users);

        ids.forEach(id -> {

            if (userIds.contains(id)) {
                return;
            }

            final HashMap<String, Object> error = new HashMap<>();
            error.put("id", id);
            error.put("message", "User not found");

            errors.add(error);
        });

        if (!errors.isEmpty()) {
            throw new NotFoundException(errors);
        }

        return users;
    }

    @Override
    public List<UUID> usersToUserIds(List<User> users) {
        return users.stream().map(BaseEntity::getId).toList();
    }
}
