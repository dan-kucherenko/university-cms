package ua.foxminded.kucherenko.task3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
