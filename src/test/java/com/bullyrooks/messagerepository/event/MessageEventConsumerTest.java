package com.bullyrooks.messagerepository.event;

import com.bullyrooks.messagerepository.event.dto.MessageEvent;
import com.bullyrooks.messagerepository.repository.MessageRepository;
import com.bullyrooks.messagerepository.repository.document.MessageDocument;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureDataMongo
@Slf4j
@ActiveProfiles("test")
@Import(TestChannelBinderConfiguration.class)
public class MessageEventConsumerTest {

    @Autowired
    MessageRepository messageRepository;


    FakeValuesService fakesvc = new FakeValuesService(
            new Locale("en-US"), new RandomService());
    Faker faker = new Faker();

    @Autowired
    private InputDestination inputDestination;

    @Test
    void testMessageStore() {

        //given
        MessageEvent msgEventIn = MessageEvent.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .message(faker.gameOfThrones().quote()).build();
        Message<MessageEvent> messageIn = new GenericMessage<>(msgEventIn);

        //when
        inputDestination.send(messageIn);

        //then
        Example<MessageDocument> example = Example.of(
                MessageDocument.builder()
                        .firstName(msgEventIn.getFirstName())
                        .lastName(msgEventIn.getLastName())
                        .message(msgEventIn.getMessage())
                        .build());
        MessageDocument doc = messageRepository.findOne(example).get();
        assertEquals(msgEventIn.getFirstName(), doc.getFirstName());
        assertEquals(msgEventIn.getLastName(), doc.getLastName());
        assertEquals(msgEventIn.getMessage(), doc.getMessage());
    }

}
