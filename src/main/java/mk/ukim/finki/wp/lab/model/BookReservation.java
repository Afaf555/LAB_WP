package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class BookReservation {
    String bookTitle;
    String readerName;
    String readerAddress;
    Long numberOfCopies;

    public String getBookTitle() {
        return bookTitle;
    }

    public BookReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies) {
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public Long getNumberOfCopies() {
        return numberOfCopies;
    }

}
