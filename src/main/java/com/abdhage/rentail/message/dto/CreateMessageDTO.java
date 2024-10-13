package com.abdhage.rentail.message.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMessageDTO {

    @Size(min = 1)
    private String content;

    @NotNull
    private UUID accommodationId;

    @NotNull
    private UUID recipientId;

    private List<String> attachments;

}
