package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookReservation {

    @Id
    @GeneratedValue
    private Long id;

    private String readerName;

    private String readerAddress;

    private Integer numCopies;   // <-- ова ти недостасува!!

    @ManyToOne
    private Book book;

    public BookReservation(String readerName, String readerAddress, Integer numCopies, Book book) {
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numCopies = numCopies;
        this.book = book;
    }
}
