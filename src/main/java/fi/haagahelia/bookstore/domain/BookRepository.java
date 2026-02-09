package fi.haagahelia.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface BookRepository extends CrudRepository<Book, Long> {

    // esim. List<Student> findByLastName(String lastName);
    // @Query annotaatiolla voi kovakoodata SQL;n
    // jos halutaan automaatti, joka hakee esim etunimellä opiskelijan niin List<Student> findByFirstName...
    // sama ,joka hakee kirjan tekijän mukaan
   // List<Book> findByAuthor(String author);
    

}
