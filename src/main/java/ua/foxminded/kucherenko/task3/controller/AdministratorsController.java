package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.kucherenko.task3.services.AdministratorService;

@Controller
@RequestMapping("/administrators")
public class AdministratorsController {
    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public String admins(Model model) {
        model.addAttribute("admins", administratorService.getAllAdmins());
        return "show_pages/admins";
    }
}
