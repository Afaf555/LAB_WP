package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    @PostConstruct
    public void init(){
        authors.add(new Author(1L, "George", "Orwell", "UK", "Author of 1984 and Animal Farm"));
        authors.add(new Author(2L, "J.K.", "Rowling", "UK", "Author of the Harry Potter series"));
        authors.add(new Author(3L, "Jane", "Austen", "UK", "Author of Pride and Prejudice"));
        for (int i = 0; i < 10; i++){
            Author author = authors.get(i % authors.size());
            books.add(new Book("Pride and Prejudice "+i,"Drama",5.5,author));
        }
        reservations=new ArrayList<>();
    }
}
