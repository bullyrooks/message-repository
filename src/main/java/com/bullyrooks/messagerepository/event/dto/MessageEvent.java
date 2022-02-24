package com.bullyrooks.messagerepository.event.dto;

import lombok.Data;

@Data
public class MessageEvent {
    private String messageId;
    private String firstName;
    private String lastName;
    private String message;
}
