package com.abdhage.rentail.service.checker;

import com.abdhage.rentail.model.User;

import java.util.List;
import java.util.UUID;

public interface UserChecker {


    public void checkUserMustUnique(String username, String email);

    public void checkUsernameMustUnique(String username);

    public void checkEmailMustUnique(String email);

    public User checkUserMustExists(UUID id);

    public List<User> checkBatchUserMustExists(List<UUID> ids);

    public List<UUID> usersToUserIds(List<User> users);

}
