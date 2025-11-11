package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        Author a1 = new Author(1L, "George", "Orwell", "UK", "Author of 1984 and Animal Farm");
        Author a2 = new Author(2L, "J.K.", "Rowling", "UK", "Author of the Harry Potter series");
        Author a3 = new Author(3L, "Jane", "Austen", "UK", "Author of Pride and Prejudice");


        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
    }

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(Long id) {
        return authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
