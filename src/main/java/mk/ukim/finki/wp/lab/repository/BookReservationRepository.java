package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookReservationRepository {
    BookReservation save(BookReservation reservation);

    //List<BookReservation> showReservations(Book book);
    List<BookReservation> findByTitle(String title);
}