//package mk.ukim.finki.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.BookReservation;
//import mk.ukim.finki.wp.lab.service.BookReservationService;
//import org.springframework.stereotype.Component;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "bookReservationServlet", urlPatterns = "/bookReservation")
//
//public class BookReservationServlet extends HttpServlet {
//    final BookReservationService bookReservationService;
//    final SpringTemplateEngine templateEngine;
//
//    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine templateEngine) {
//        this.bookReservationService = bookReservationService;
//        this.templateEngine = templateEngine;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String bookTitle = req.getParameter("bookTitle");
//        String readerName = req.getParameter("readerName");
//        String readerAddress = req.getParameter("readerAddress");
//        int numCopies = Integer.parseInt(req.getParameter("numCopies"));
//        String clientIp = req.getRemoteAddr();
//        BookReservation reservation = bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("bookTitle", bookTitle);
//        context.setVariable("readerName", readerName);
//        context.setVariable("numCopies", numCopies);
//        context.setVariable("clientIp", clientIp);
//        templateEngine.process("reservationConfirmation.html", context, resp.getWriter());
//
//    }
//}
