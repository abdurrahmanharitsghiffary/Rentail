package com.abdhage.rentail.message;

import com.abdhage.rentail.message.dto.CreateMessageDTO;
import com.abdhage.rentail.message.dto.UpdateMessageDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("accommodations/{id}/messages")
    public List<MessageResponse> findAll(@PathVariable UUID id) {
        return messageService.findByAccommodation(id);
    }

    @GetMapping("messages/{id}")
    public MessageResponse findOne(@PathVariable UUID id) {
        return messageService.findOne(id);
    }

    @PostMapping("messages")
    public MessageResponse create(@Valid @RequestBody CreateMessageDTO createMessageDTO) {
        return messageService.create(createMessageDTO);
    }

    @PatchMapping("messages/{id}")
    public MessageResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateMessageDTO updateMessageDto) {
        return messageService.update(id, updateMessageDto);
    }

    @DeleteMapping("messages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable UUID id) {
        messageService.destroy(id);
    }

}
