package fi.haagahelia.bookstore.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@Controller
public class BookController {
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    //lisää kirja endpointilla "/add"
    @RequestMapping(value = "/add")
    public String addbook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    //tallenna kirja endpointilla "/save"
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    //poista kirja
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    // edit kirja endpoint 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookid, Model model) {
        model.addAttribute("book", repository.findById(bookid));
        /* Book book = repository.findById(bookid)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Book id" + bookid)); */
        /* model.addAttribute("book", book); */
        return "addbook";
    }
    
    @RequestMapping("/index")
    public String BookHome() {
        return "index";
    }

}
