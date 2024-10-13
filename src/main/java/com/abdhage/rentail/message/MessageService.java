package com.abdhage.rentail.message;

import com.abdhage.rentail.common.service.BaseCrudService;
import com.abdhage.rentail.message.dto.CreateMessageDTO;
import com.abdhage.rentail.message.dto.UpdateMessageDTO;
import com.abdhage.rentail.message.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService extends BaseCrudService<MessageResponse, CreateMessageDTO, UpdateMessageDTO, UUID> {
    public List<MessageResponse> findByAccommodation(UUID id);

    public Message checkMessageMustExists(UUID id);
}
