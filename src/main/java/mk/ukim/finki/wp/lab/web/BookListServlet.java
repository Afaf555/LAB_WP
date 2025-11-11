package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookListServlet", urlPatterns = "")

public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String searchText = req.getParameter("searchText");
        String ratingParam = req.getParameter("minRating");
        Double minRating = null;

        if (ratingParam != null && !ratingParam.isEmpty()) {
            try {
                minRating = Double.parseDouble(ratingParam);
            } catch (NumberFormatException ignored) {}
        }

        List<Book> books;
        if ((searchText != null && !searchText.isEmpty()) || minRating != null) {
            books = bookService.searchBooks(searchText, minRating);
        } else {
            books = bookService.listAll();
        }

        context.setVariable("books", books);
        context.setVariable("searchText", searchText);
        context.setVariable("minRating", ratingParam);

        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }

}
