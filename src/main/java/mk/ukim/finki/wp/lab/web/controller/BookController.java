package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Book> books = bookService.listAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    // ADD FORM
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {

        bookService.saveBook(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        Author author=authorService.findAll().stream().filter(a->a.getId().equals(authorId)).findFirst().orElse(null);

        bookService.editBook(bookId, title, genre, averageRating, authorId);
//        if(author==null)
//        bookService.saveBook(bookId, title, genre, averageRating, authorId)
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
    @PostMapping("/books/book-form")
    public String getAddBookPage(Model model){
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }
    @PostMapping("/books/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model){
        Book book=bookService.findById(id);
        if(book!=null){
            return "redirect:/books?error=BookNotFound";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

}
