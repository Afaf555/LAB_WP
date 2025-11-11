package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
//@AllArgsConstructor
@Data

public class Book {
    private String title;
    private String genre;
    private double averageRating;
    private Long id;
    private Author author;
    public Book() {
    }

    public Book(String title, String genre, double averageRating,Author author) {
        this.id=(long)(Math.random()*1000);
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}


