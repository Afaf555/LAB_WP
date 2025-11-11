package mk.ukim.finki.wp.lab.repository;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(book ->
                        (text == null || text.isEmpty() ||
                                book.getTitle().toLowerCase().contains(text.toLowerCase())) &&
                                (rating == null || book.getAverageRating() >= rating)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Book addBook(String title, String genre, Double averageRating, Long authorId) {
        Author author = DataHolder.authors.stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElse(null);

        // backup проверка - ако не најде author со тоа id
        if (author == null && !DataHolder.authors.isEmpty()) {
            // земи го првиот author (или направи логика според име ако сакаш)
            author = DataHolder.authors.get(0);
        }

        Book book = new Book(title, genre, averageRating, author);
        book.setId((long)(Math.random() * 1000));
        DataHolder.books.add(book);
        return book;
    }



    @Override
    public Book editBook(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        Book book = findById(bookId);
        if (book == null) {
            return null;
        }

        Author author = DataHolder.authors.stream()
                .filter(a -> a.getId().equals(authorId)
                        || (book.getAuthor() != null && a.getName().equals(book.getAuthor().getName())
                        && a.getSurname().equals(book.getAuthor().getSurname())))
                .findFirst()
                .orElse(null);

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return book;
    }



    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.books.removeIf(b -> b.getId().equals(id));
    }

}
