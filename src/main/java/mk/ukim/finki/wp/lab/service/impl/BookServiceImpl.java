package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book saveBook(String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);

        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    @Override
    public Book editBook(Long id, String title, String genre, Double averageRating, Long authorId) {
        Book book = findById(id);
        if (book == null) return null;

        Author author = authorRepository.findById(authorId).orElse(null);

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return bookRepository.save(book);
    }
    @Override
    public List<Book> findByAuthor(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.findAll()   // basic search
                .stream()
                .filter(b -> (text == null || b.getTitle().toLowerCase().contains(text.toLowerCase())))
                .filter(b -> (rating == null || b.getAverageRating() >= rating))
                .toList();
    }
}
