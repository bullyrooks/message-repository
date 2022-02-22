package com.bullyrooks.messagerepository.repository.mapper;

import com.bullyrooks.messagerepository.repository.document.MessageDocument;
import com.bullyrooks.messagerepository.service.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageDocumentMapper {
    MessageDocumentMapper INSTANCE = Mappers.getMapper(MessageDocumentMapper.class);

    MessageDocument modelToDocument(Message model);

    Message documentToModel(MessageDocument returnDoc);
}

