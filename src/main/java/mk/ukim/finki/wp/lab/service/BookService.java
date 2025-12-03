package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);

    Book saveBook(String title, String genre, Double averageRating, Long authorId);

    Book editBook(Long bookId, String title, String genre, Double averageRating, Long authorId);

    void deleteBook(Long bookId);

    Book findById(Long bookId);

    List<Book> findByAuthor(Long authorId);
    //Book findBookByName(String title);
}
