package com.bullyrooks.messagerepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureDataMongo
@ActiveProfiles("test")
class MessageRepositoryApplicationTests {

	@Test
	void contextLoads() {
	}

}
