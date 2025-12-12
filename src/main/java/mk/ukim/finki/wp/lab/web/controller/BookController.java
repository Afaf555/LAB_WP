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

    public BookController(BookService bookService,
                          AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Long authorId,
            Model model)
    {
        System.out.println("Authors size = " + authorService.findAll().size());

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("selectedAuthorId", authorId);

        // 1. Прво земи ги сите книги
        List<Book> books = bookService.listAll();

        // 2. Филтрирање по title
        if (searchText != null && !searchText.isEmpty()) {
            books = books.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(searchText.toLowerCase()))
                    .toList();
        }

        // 3. Филтрирање по рейтинг
        if (minRating != null) {
            books = books.stream()
                    .filter(b -> b.getAverageRating() >= minRating)
                    .toList();
        }

        // 4. Филтрирање по authorId
        if (authorId != null) {
            books = books.stream()
                    .filter(b -> b.getAuthor().getId().equals(authorId))
                    .toList();
        }

        // 5. Ги ставаме параметрите назад во HTML
        model.addAttribute("searchText", searchText);
        model.addAttribute("minRating", minRating);

        model.addAttribute("books", books);

        return "listBooks";
    }




    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("authors", authorService.findAll());
        // за да знае book-form дека е add, ќе нема "book" атрибут
        return "book-form";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            // fallback ако не постои
            return "redirect:/books";
        }
        model.addAttribute("book", book);
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

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {

        bookService.editBook(id, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
