package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.bookstore.web.BookController;
import fi.haagahelia.bookstore.web.BookRestController;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController controller;

	@Autowired
	private BookRestController restController;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(restController).isNotNull();
	}

}
