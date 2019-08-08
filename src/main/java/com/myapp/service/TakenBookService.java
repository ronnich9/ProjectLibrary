package com.myapp.service;

import com.myapp.entity.Book;
import com.myapp.entity.TakenBook;
import com.myapp.entity.User;
import com.myapp.repository.BookRepository;
import com.myapp.repository.TakenBooksRepository;
import com.myapp.repository.UserRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class TakenBookService {

    private final TakenBooksRepository takenBooksRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public TakenBookService(TakenBooksRepository takenBooksRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.takenBooksRepository = takenBooksRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<TakenBook> getAll() {
       return takenBooksRepository.findAll();
    }

    public Optional<TakenBook> findById(Long id) {
        return takenBooksRepository.findById(id);
    }

    public void addTakenBook(Long userId, Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("Invalid book id: " + bookId));
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("Invalid user id: " + userId));

        TakenBook takenBook = TakenBook.builder()
                .user(user)
                .book(book)
                .taken_time(LocalDateTime.now())
                .returned_time(LocalDateTime.now().plusMonths(1))
                .build();
        user.getTakenBooks().add(takenBook);
        userRepository.save(user);
        log.info("taken book success!");
    }

    public List<TakenBook> getTakenBooksById(Long bookId) {
        return takenBooksRepository.findByBookId(bookId);
    }

    public List<TakenBook> getTakenBooksByUserId(Long userId) {
        return takenBooksRepository.findByUserId(userId);
    }

    public void delete(TakenBook takenBook) {
        takenBooksRepository.delete(takenBook);
    }

}
