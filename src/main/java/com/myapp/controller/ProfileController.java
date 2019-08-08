package com.myapp.controller;

import com.myapp.entity.User;
import com.myapp.service.TakenBookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class ProfileController {

    private final TakenBookService takenBookService;

    public ProfileController(TakenBookService takenBookService) {
        this.takenBookService = takenBookService;
    }


    @GetMapping("/profile")
    public String showAll(@AuthenticationPrincipal User user, Model model) {
        log.info(user.getId() + " user id lets see!");
        if (user.getId() != null) {
            model.addAttribute("takenBooks", takenBookService.getTakenBooksByUserId(user.getId()));
        }
        return "profile/profile";
    }
}
