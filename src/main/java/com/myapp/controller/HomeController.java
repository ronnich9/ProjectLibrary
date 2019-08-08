package com.myapp.controller;


import com.myapp.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log4j2
@Controller
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showMain() {
        return "index";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String bookTitle, Model model) {
        model.addAttribute("books", bookService.searchBooksByTitle(bookTitle));
        model.addAttribute("searchValue", bookTitle);
        return "index";
    }
}
