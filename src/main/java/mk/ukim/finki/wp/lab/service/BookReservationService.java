package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookReservationService {
    BookReservation placeReservation(String bookTitle,
                                     String readerName,
                                     String readerAddress,
                                     Integer numCopies);
    // List<BookReservation> showReservations(Book book);
    List<BookReservation> getResByTitle(String bookTitle);
    public List<BookReservation> findByBookTitle(String title);

}
