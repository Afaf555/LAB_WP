package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository bookReservationRepository;
    private final BookRepository bookRepository;

    public BookReservationServiceImpl(BookReservationRepository bookReservationRepository, BookRepository bookRepository) {
        this.bookReservationRepository = bookReservationRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle,
                                            String readerName,
                                            String readerAddress,
                                            Integer numCopies) {

        Book book = bookRepository.findByTitle(bookTitle).orElse(null);

        BookReservation reservation =
                new BookReservation(readerName, readerAddress, numCopies, book);

        return bookReservationRepository.save(reservation);
    }

    @Override
    public List<BookReservation> getResByTitle(String title) {
        return bookReservationRepository.findAllByBook_Title(title);
    }

    @Override
    public List<BookReservation> findByBookTitle(String title) {
        return bookReservationRepository.findAll()
                .stream()
                .filter(r -> r.getBook().getTitle().equals(title))
                .toList();
    }

}
