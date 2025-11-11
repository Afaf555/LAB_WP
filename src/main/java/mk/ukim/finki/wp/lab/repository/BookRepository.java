package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);

    Book addBook(String title, String genre, Double averageRating, Long authorId);
    Book editBook(Long bookId, String title, String genre, Double averageRating, Long authorId);
    void deleteById(Long bookId);
    Book findById(Long bookId);
}

