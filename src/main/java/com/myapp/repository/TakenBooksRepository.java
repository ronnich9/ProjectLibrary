package com.myapp.repository;

import com.myapp.entity.Book;
import com.myapp.entity.TakenBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TakenBooksRepository extends CrudRepository<TakenBook, Long> {

    List<TakenBook> findAll();

    List<TakenBook> findByBookId(Long bookId);

    List<TakenBook> findByUserId(Long userId);

    //List<TakenBook> findDistinctByBookIdIn(List<Long> booksIds);

}
