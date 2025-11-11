package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AuthorRepository {
    private List<Author> authors=new ArrayList<>();
    public AuthorRepository() {
        Author a1 = new Author((long)(Math.random()*1000), "George", "Orwell", "UK", "Author of 1984 and Animal Farm");
        Author a2 = new Author((long)(Math.random()*1000), "J.K.", "Rowling", "UK", "Author of the Harry Potter series");
        Author a3 = new Author((long)(Math.random()*1000), "Jane", "Austen", "UK", "Author of Pride and Prejudice");

        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
    }
    public List<Author> findAll() {
        return authors;
    }
}
