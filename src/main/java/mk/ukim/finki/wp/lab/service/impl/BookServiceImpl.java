package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if ((text == null || text.isEmpty()) && rating == null) {
            return listAll();
        }
        return this.bookRepository.searchBooks(text, rating);
    }

    @Override
    public Book saveBook(String title, String genre, Double averageRating, Long authorId) {
        return this.bookRepository.addBook(title, genre, averageRating, authorId);
    }

    @Override
    public Book editBook(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        return this.bookRepository.editBook(bookId, title, genre, averageRating, authorId);
    }

    @Override
    public void deleteBook(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }

    @Override
    public Book findById(Long bookId) {
        return this.bookRepository.findById(bookId);
    }
}
