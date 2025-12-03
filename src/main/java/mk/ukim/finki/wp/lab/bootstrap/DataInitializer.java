package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"prod", "h2"})
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository,
                           BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {

        // AUTHORS
        Author a1 = new Author(null, "George", "Orwell", "UK",
                "Author of 1984 and Animal Farm");

        Author a2 = new Author(null, "J.K.", "Rowling", "UK",
                "Author of the Harry Potter series");

        Author a3 = new Author(null, "Jane", "Austen", "UK",
                "Author of Pride and Prejudice");

        a1 = authorRepository.save(a1);
        a2 = authorRepository.save(a2);
        a3 = authorRepository.save(a3);

        // BOOKS
        bookRepository.save(new Book(
                null, "1984", "Dystopian", 9.0, a1));

        bookRepository.save(new Book(
                null, "Harry Potter", "Fantasy", 9.5, a2));

        bookRepository.save(new Book(
                null, "Pride and Prejudice", "Romance", 8.7, a3));
    }
}
