package com.myapp.controller;


import com.myapp.entity.Book;
import com.myapp.entity.User;
import com.myapp.service.AuthorService;
import com.myapp.service.BookService;
import com.myapp.service.TakenBookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private final TakenBookService takenBookService;

    public BookController(BookService bookService, AuthorService authorService, TakenBookService takenBookService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.takenBookService = takenBookService;
    }

    @GetMapping("/books")
    public String showAll(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/books";
    }

    @RequestMapping("/books/create")
    public String createNewBook(Model model) {
        model.addAttribute("newBook", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/create_book";
    }

    @PostMapping("/books/create")
    public String saveBook(@ModelAttribute Book newBook) {
        bookService.saveBook(newBook);
        //model.addAttribute("books", bookService.findAll());
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/update_book";
    }

    @PostMapping("/books/update/{id}")
    public String updateUser(@PathVariable("id") long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "book/update_book";
        }

        bookService.saveBook(book);
        model.addAttribute("books", bookService.getAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        bookService.delete(book);
        model.addAttribute("books", bookService.getAllBooks());
        return "redirect:/books";
    }

    @PostMapping("/books/take/{id}")
    public String takeBook(@AuthenticationPrincipal User user,
                                      @PathVariable("id") long bookId) {


        takenBookService.addTakenBook(user.getId(), bookId);
        return "redirect:/profile";
    }

}
