package com.bullyrooks.messagerepository.repository.mapper;

import com.bullyrooks.messagerepository.event.dto.MessageEvent;
import com.bullyrooks.messagerepository.repository.document.MessageDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageDocumentMapper {
    MessageDocumentMapper INSTANCE = Mappers.getMapper(MessageDocumentMapper.class);

    MessageDocument eventToDocument(MessageEvent msgEvent);

    MessageEvent documentToEvent(MessageDocument returnDoc);
}

