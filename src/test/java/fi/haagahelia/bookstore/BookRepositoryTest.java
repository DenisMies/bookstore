package fi.haagahelia.bookstore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;



@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void createNewBook() {
        Category category = new Category("Programming");
        categoryRepository.save(category);

        Book book = new Book("Test Driven Development", "Kent Beck", 2003, "9780321146533", 40, category);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void searchBookByName() {
        Category category = new Category("Programming");
        categoryRepository.save(category);

        Book book = new Book("Test Driven Development", "Kent Beck", 2003, "9780321146533", 40, category);
        repository.save(book);

        List<Book> books = repository.findByTitle("Test Driven Development");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Kent Beck");
    }

    @Test
    public void deleteBook() {
        Category category = new Category("Programming");
        categoryRepository.save(category);

        Book book = new Book("Test Driven Development", "Kent Beck", 2003, "9780321146533", 40, category);
        repository.save(book);

        repository.delete(book);
        List<Book> books = repository.findByTitle("Test Driven Development");
        assertThat(books).isEmpty();
    }
}

