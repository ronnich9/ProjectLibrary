package com.myapp.service;

import com.myapp.entity.Book;
import com.myapp.repository.BookRepository;
import com.myapp.repository.TakenBooksRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class BookService {

    private BookRepository bookRepository;
    private TakenBooksRepository takenBooksRepository;

    public BookService(BookRepository bookRepository, TakenBooksRepository takenBooksRepository) {
        this.bookRepository = bookRepository;
        this.takenBooksRepository = takenBooksRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    };

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllUniqueTakenBooks() {
        List<Long> array = new ArrayList<>();
        for (int i=0; i < takenBooksRepository.findAll().size(); i++) {
            array.add(takenBooksRepository.findAll().get(i).getBook().getId());
        }

        return bookRepository.findDistinctByIdIn(array);
    }

    public List<Book> searchBooksByTitle(String bookTitle) {
        List<Book> booksFound = bookRepository.findAllByTitleContainingIgnoreCase(bookTitle);
        log.info("SEARCH BOOKS WITH TITLE -> " + bookTitle + " FOUND " + booksFound.size());
        return booksFound;
    }
}
