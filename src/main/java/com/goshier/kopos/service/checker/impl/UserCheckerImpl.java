package com.goshier.kopos.service.checker.impl;

import com.goshier.kopos.exception.EmailMustUniqueException;
import com.goshier.kopos.exception.UserNotFoundException;
import com.goshier.kopos.exception.UsernameMustUniqueException;
import com.goshier.kopos.exception.common.NotFoundException;
import com.goshier.kopos.model.common.BaseEntity;
import com.goshier.kopos.model.User;
import com.goshier.kopos.repository.UserRepository;
import com.goshier.kopos.service.checker.UserChecker;
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
