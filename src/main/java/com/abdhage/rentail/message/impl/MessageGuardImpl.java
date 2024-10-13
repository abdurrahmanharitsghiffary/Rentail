package com.abdhage.rentail.message.impl;

import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.common.exception.ForbiddenException;
import com.abdhage.rentail.message.MessageGuard;
import com.abdhage.rentail.message.MessageRepository;
import com.abdhage.rentail.message.model.Message;
import com.abdhage.rentail.user.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageGuardImpl implements MessageGuard {

    private final MessageRepository messageRepository;
    private final AuthService authService;
    ;

    public MessageGuardImpl(MessageRepository messageRepository, AuthService authService) {
        this.messageRepository = messageRepository;
        this.authService = authService;
    }

    @Override
    public void verifyPermission(UUID id) {
        User loggedUser = authService.getLoggedUserInformations();

        messageRepository.findById(id).ifPresent(message -> {
            if (message.getAuthor().getId() != loggedUser.getId()) {
                throw new ForbiddenException();
            }
        });
    }

    @Override
    public void verifyPermission(Message message) {
        User loggedUser = authService.getLoggedUserInformations();

        if (message.getAuthor().getId() != loggedUser.getId()) {
            throw new ForbiddenException();
        }
    }

}
