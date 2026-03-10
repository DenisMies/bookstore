package fi.haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	@Bean
	public CommandLineRunner bookdemo(BookRepository repository, CategoryRepository categoryRepository,
		UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		return (args) -> {
			User user1 = new User("user", passwordEncoder.encode("user"),"user@example.com", "USER");
			
			userRepository.save(new User("admin",passwordEncoder.encode("admin"), "admin@example.com","ADMIN"));

			Category Fantasy = categoryRepository.save(new Category("Fantasy"));
			Category History = categoryRepository.save(new Category("History"));
			Category Thriller = categoryRepository.save(new Category("Thriller"));
			Category Scifi = categoryRepository.save(new Category("Science Fiction"));
			Category Programming = categoryRepository.save(new Category("Programming"));

			Book book1 = new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 25, Programming);
			Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "9780261103344", 20, Fantasy);
			repository.save(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 55, Programming));
			repository.save(new Book("Sapiens", "Yuval Noah Harari", 2011, "9780062316097", 35, History));
			repository.save(new Book("Dune", "Frank Herbert", 1965, "9780441172719", 30, Scifi));
			repository.save(new Book("The Da Vinci Code", "Dan Brown", 2003, "9780307474278", 22, Thriller));

			
			repository.save(book1);
			repository.save(book2); 
			userRepository.save(user1);
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
