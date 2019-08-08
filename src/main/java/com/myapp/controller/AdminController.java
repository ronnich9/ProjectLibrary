package com.myapp.controller;

import com.myapp.entity.Book;
import com.myapp.entity.TakenBook;
import com.myapp.service.BookService;
import com.myapp.service.TakenBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TakenBookService takenBookService;
    private final BookService bookService;

    public AdminController(TakenBookService takenBookService, BookService bookService) {
        this.takenBookService = takenBookService;
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String showAllAdminBooks(Model model) {
        model.addAttribute("takenBooks", takenBookService.getAll());
        return "admin/admin_all_books";
    }

    @GetMapping("/books")
    public String showAdminBooks(Model model) {
        model.addAttribute("books", bookService.getAllUniqueTakenBooks());
        return "admin/admin_books";
    }

    @GetMapping("/books/show/{id}")
    public String showSortedByBook(@PathVariable("id") long bookId, Model model) {
        model.addAttribute("allTakenBooks", takenBookService.getTakenBooksById(bookId));
        return "admin/users";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteUser(@PathVariable("id") long takenBookId, Model model) {
        TakenBook takenBook = takenBookService.findById(takenBookId).orElseThrow(() ->
                new IllegalArgumentException("Invalid takenBookId Id:" + takenBookId));
        takenBookService.delete(takenBook);
        model.addAttribute("allTakenBooks", takenBookService.getTakenBooksById(takenBook.getBook().getId()));
        return "admin/users";
    }


}
