package com.abdhage.rentail.message;

import com.abdhage.rentail.message.dto.CreateMessageDTO;
import com.abdhage.rentail.message.dto.UpdateMessageDTO;
import com.abdhage.rentail.message.model.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    public MessageResponse messageToMessageResponse(Message message);

    public Message messageResponseToMessage(MessageResponse message);

    public Message createMessageDtoToMessage(CreateMessageDTO dto);

    public Message updateMessageDtoToMessage(UpdateMessageDTO dto);
}
