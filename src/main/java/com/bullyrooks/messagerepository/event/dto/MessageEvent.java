package com.bullyrooks.messagerepository.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageEvent {
    private String messageId;
    private String firstName;
    private String lastName;
    private String message;
}
