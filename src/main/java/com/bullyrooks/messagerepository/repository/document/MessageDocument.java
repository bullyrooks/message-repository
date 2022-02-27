package com.bullyrooks.messagerepository.repository.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("messages")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDocument {
    @Id
    private String messageId;
    private String firstName;
    private String lastName;
    private String message;
}
