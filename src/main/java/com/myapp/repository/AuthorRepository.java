package com.myapp.repository;

import com.myapp.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    public List<Author> findAll();

    public List<Author> findAllByNameContainingIgnoreCase(String authorName);
}
