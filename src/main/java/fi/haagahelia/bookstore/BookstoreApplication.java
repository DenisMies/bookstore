package fi.haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	@Bean
	public CommandLineRunner bookdemo(BookRepository repository) {
		return (args) -> {
			Book book1 = new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 25);
			Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "9780261103344", 20);
			repository.save(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 55));
			
			repository.save(book1);
			repository.save(book2); 
			// VOI Tallentaa suoraan tietokantaan -> repository.save(new Book...)
			log.info("fetch all books");
			for (Book book : repository.findAll())
			log.info(book.toString());
		};
	}

}

/* 
esimerkki

Book book1 = new Book(
    "Clean Code",
    "Robert C. Martin",
    2008,
    "9780132350884",
    45
);

Book book2 = new Book(
    "The Hobbit",
    "J.R.R. Tolkien",
    1937,
    "9780261103344",
    25
);

Book book3 = new Book(
    "Effective Java",
    "Joshua Bloch",
    2018,
    "9780134685991",
    55
);


@Bean
 * public CommandLineRunner studentDemo(StudentRepository repository) {
 * return (args) -> { // args ei ole varattu sana voi olla esim parametrit
 * log.info("sava a couple of students");
 * repository.save(new Student("Aku", "Johnson", "john@john.com"));
 * repository.save(new Student("Ankka", "Kateson", "kate@kate.com"));
 * 
 * log.info("fetch all students");
 * for (Student student : repository.findAll())
 * 	log.info(student.toString());
 * }
 * 
 * }
 */
