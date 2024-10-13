package com.abdhage.rentail.message.impl;

import com.abdhage.rentail.accommodation.AccommodationChecker;
import com.abdhage.rentail.accommodation.AccommodationService;
import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.common.exception.NotFoundException;
import com.abdhage.rentail.message.*;
import com.abdhage.rentail.message.dto.CreateMessageDTO;
import com.abdhage.rentail.message.dto.UpdateMessageDTO;
import com.abdhage.rentail.message.model.Message;
import com.abdhage.rentail.user.UserChecker;
import com.abdhage.rentail.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final MessageGuard messageGuard;
    private final AccommodationChecker accommodationChecker;
    private final AuthService authService;
    private final UserChecker userChecker;

    public MessageServiceImpl(MessageRepository messageRepository,
                              MessageMapper messageMapper, MessageGuard messageGuard, AccommodationChecker accommodationChecker, AuthService authService, UserChecker userChecker) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.messageGuard = messageGuard;
        this.accommodationChecker = accommodationChecker;
        this.authService = authService;
        this.userChecker = userChecker;
    }

    @Override
    public List<MessageResponse> findAll() {
        return messageRepository
                .findAll()
                .stream()
                .map(messageMapper::messageToMessageResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse create(CreateMessageDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();
        User recipient = userChecker.checkUserMustExists(dto.getRecipientId());

        Accommodation accommodation = accommodationChecker
                .checkAccommodationMustExists(dto.getAccommodationId());
        Message message = messageMapper.createMessageDtoToMessage(dto);

        message.setRecipient(recipient);
        message.setAccommodation(accommodation);
        message.setAuthor(loggedUser);

        return messageMapper.messageToMessageResponse(messageRepository.save(message));
    }

    @Override
    public MessageResponse update(UUID uuid, UpdateMessageDTO dto) {
        final Message message = checkMessageMustExists(uuid);
        messageGuard.verifyPermission(message);

        return messageMapper.messageToMessageResponse(messageMapper.updateMessageDtoToMessage(dto));
    }

    @Override
    public void destroy(UUID uuid) {
        final Message message = checkMessageMustExists(uuid);
        messageGuard.verifyPermission(message);

        messageRepository.deleteById(uuid);
    }

    @Override
    public MessageResponse findOne(UUID uuid) {
        return messageMapper.messageToMessageResponse(checkMessageMustExists(uuid));
    }

    @Override
    public List<MessageResponse> findByAccommodation(UUID id) {
        return messageRepository
                .findByAccommodation_Id(id)
                .stream()
                .map(messageMapper::messageToMessageResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Message checkMessageMustExists(UUID id) {
        return messageRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Message not found"));
    }
}
