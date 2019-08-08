package com.myapp.controller;

import com.myapp.entity.Author;
import com.myapp.entity.Book;
import com.myapp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String authors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author/authors";
    }


    @RequestMapping("/authors/create")
    public String addNewAuthor(Model model) {
        model.addAttribute("newAuthor", new Author());
        return "author/create_author";
    }

    @PostMapping("/authors/create")
    public String saveNewAuthor(@ModelAttribute Author newAuthor) {
        authorService.saveAuthor(newAuthor);
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Author author = authorService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        authorService.delete(author);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "redirect:/authors";
    }
}
