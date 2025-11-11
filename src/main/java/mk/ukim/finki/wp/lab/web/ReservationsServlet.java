package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "reservationsServlet", urlPatterns = "/reservations")
public class ReservationsServlet extends HttpServlet {

    private final BookReservationService bookReservationService;
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public ReservationsServlet(BookReservationService bookReservationService, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String readerName = req.getParameter("readerName");
//        String readerAddress = req.getParameter("readerAddress");
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        String bookTitle = req.getParameter("title");
        List<BookReservation> bookReservationList =bookReservationService.getResByTitle(bookTitle);
        context.setVariable("bookReservationList", bookReservationList);
        springTemplateEngine.process("reservationsNew.html", context, resp.getWriter());
    }
}
