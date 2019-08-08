package com.myapp.service;

import com.myapp.entity.Author;
import com.myapp.entity.Book;
import com.myapp.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }

    public void saveAuthor(Author newAuthor) {


        authorRepository.save(newAuthor);


    }

}
