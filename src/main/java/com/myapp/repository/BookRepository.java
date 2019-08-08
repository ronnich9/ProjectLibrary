package com.myapp.repository;

import com.myapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    List<Book> findDistinctByIdIn(List<Long> array);

    List<Book> findAllByTitleContainingIgnoreCase(String bookTitle);


}
