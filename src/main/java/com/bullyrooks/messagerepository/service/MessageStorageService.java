package com.bullyrooks.messagerepository.service;

import com.bullyrooks.messagerepository.config.LoggingEnabled;
import com.bullyrooks.messagerepository.repository.MessageRepository;
import com.bullyrooks.messagerepository.repository.document.MessageDocument;
import com.bullyrooks.messagerepository.repository.mapper.MessageDocumentMapper;
import com.bullyrooks.messagerepository.service.model.Message;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@LoggingEnabled
public class MessageStorageService {

    MessageRepository messageRepository;
    MeterRegistry logzioMeterRegistry;

    Counter messageSaved;

    @Autowired
    public MessageStorageService(MessageRepository messageRepository,
                                 MeterRegistry logzioMeterRegistry){
        this.messageRepository = messageRepository;
        this.logzioMeterRegistry = logzioMeterRegistry;
        initCounters();
    }

    private void initCounters() {
        messageSaved = Counter.builder("message.stored.count")
                .description("Number of messages successfully stored in the repository")
                .register(logzioMeterRegistry);
    }


    public Message saveMessage(Message message){

        MessageDocument msgDoc = MessageDocumentMapper.INSTANCE.modelToDocument(message);

        log.info("saving document: {}", msgDoc);
        MessageDocument returnDoc = messageRepository.save(msgDoc);
        messageSaved.increment();
        return MessageDocumentMapper.INSTANCE.documentToModel(returnDoc);
    }
}
