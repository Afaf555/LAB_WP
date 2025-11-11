package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {
    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;    }

//    @Override
//    public List<BookReservation> showReservations(Book book) {
//        return DataHolder.reservations.stream().filter(i -> i.getBookTitle().equalsIgnoreCase(book.getTitle())).toList();
//    }

    public List<BookReservation> findByTitle(String title) {
        return DataHolder.reservations.stream().filter(i -> i.getBookTitle().equals(title)).toList();
    }
}
