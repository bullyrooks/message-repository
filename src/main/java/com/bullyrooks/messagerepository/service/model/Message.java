package com.bullyrooks.messagerepository.service.model;

import lombok.Data;

@Data
public class Message {
    private String messageId;
    private String firstName;
    private String lastName;
    private String message;
}
