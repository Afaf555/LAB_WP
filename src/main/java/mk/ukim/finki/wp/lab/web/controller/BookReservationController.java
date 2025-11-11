package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }
    @PostMapping("/bookReservation")
    public String placeReservation( String bookTitle,
                                    String readerName,
                                    String readerAddress,
                                    Long numCopies,
                                   HttpServletRequest request,
                                   Model model) {

        if (numCopies == null) {
            numCopies = 1L; // default 1 copy
        }

        String clientIp = request.getRemoteAddr();

        BookReservation reservation = bookReservationService.placeReservation(
                bookTitle, readerName, readerAddress, numCopies
        );

        model.addAttribute("readerName", reservation.getReaderName());
        model.addAttribute("bookTitle", reservation.getBookTitle());
        model.addAttribute("numCopies", reservation.getNumberOfCopies());
        model.addAttribute("clientIp", clientIp);

        return "reservationConfirmation";
    }

}
