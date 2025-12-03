package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    private final BookReservationService reservationService;

    public ReservationController(BookReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/bookReservation")
    public String makeReservation(@RequestParam String bookTitle,
                                  @RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam Integer numCopies,
                                  Model model) {

        BookReservation reservation =
                reservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        model.addAttribute("readerName", reservation.getReaderName());
        model.addAttribute("bookTitle", reservation.getBook().getTitle());
        model.addAttribute("readerAddress", reservation.getReaderAddress());
        model.addAttribute("numCopies", reservation.getNumCopies());

        return "reservationConfirmation";
    }

    @GetMapping("/reservations")
    public String showReservations(@RequestParam String title, Model model) {

        List<BookReservation> reservations = reservationService.findByBookTitle(title);

        model.addAttribute("bookReservationList", reservations);
        model.addAttribute("bookTitle", title);

        return "reservationsNew";
    }
}
