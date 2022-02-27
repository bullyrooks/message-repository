package com.bullyrooks.messagerepository.event;

import com.bullyrooks.messagerepository.event.dto.MessageEvent;
import com.bullyrooks.messagerepository.repository.mapper.MessageDocumentMapper;
import com.bullyrooks.messagerepository.service.MessageStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class MessageEventConsumer {

    @Autowired
    MessageStorageService storageService;

    @Bean
    public Consumer<MessageEvent> consumeMessageEvent(){
        return (eventIn) -> storageService
                .saveMessage(MessageDocumentMapper.INSTANCE.eventToDocument(eventIn));
    }
}
