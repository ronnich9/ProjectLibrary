package com.myapp.controller;

import com.myapp.dto.UserDTO;
import com.myapp.exception.DontExistException;
import com.myapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = "/registration")
public class RegistrationFormController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String addUser(Model model, UserDTO userDTO) {
        userService.saveNewUser(userDTO);
        model.addAttribute("result", "User successfully registered!");
        return "registration";
    }

    @ExceptionHandler(DontExistException.class)
    public String addException(Model model, DontExistException e) {
        model.addAttribute("result", e.getMessage());
        return "registration";
    }

}
