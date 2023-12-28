package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.services.AdministratorService;

@Controller
@RequestMapping("/administrators")
public class AdministratorsController {
    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public String admins(Model model,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Administrator> adminsPage = administratorService.getAllAdmins(page, size);
        model.addAttribute("admins", adminsPage.getContent());
        model.addAttribute("pageNo", page + 1);
        model.addAttribute("totalPages", adminsPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "show_pages/admins";
    }
}
