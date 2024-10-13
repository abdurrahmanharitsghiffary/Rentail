package com.abdhage.rentail.message;

import com.abdhage.rentail.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private User author;
    private User recipient;
    private String content;
    private List<String> attachments;

}
