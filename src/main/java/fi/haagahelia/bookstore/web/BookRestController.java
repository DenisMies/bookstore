package fi.haagahelia.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@RestController
public class BookRestController {
    private final BookRepository repository;
    /* private final CategoryRepository categoryRepository; */

    public BookRestController(BookRepository repository) {
        this.repository = repository;
    }
    // rest hae kaikki kirjat -> http://localhost:8080/books
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> BookListRest() {
        return (List<Book>) repository.findAll();
    }
    // hae kirja id numerolla -> http://localhost:8080/books/1
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookid) { // quick fix lisäsi: (Optional) <Book>
        return repository.findById(bookid); //.orElse(null)
    }


}
