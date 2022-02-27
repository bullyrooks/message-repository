package com.bullyrooks.messagerepository.repository;

import com.bullyrooks.messagerepository.repository.document.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<MessageDocument, String> {

}
