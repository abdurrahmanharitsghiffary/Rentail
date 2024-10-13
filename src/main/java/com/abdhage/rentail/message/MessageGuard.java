package com.abdhage.rentail.message;

import com.abdhage.rentail.message.model.Message;

import java.util.UUID;

public interface MessageGuard {
    public void verifyPermission(UUID id);

    public void verifyPermission(Message message);
}
