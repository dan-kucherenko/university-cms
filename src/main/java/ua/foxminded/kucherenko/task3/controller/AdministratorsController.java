package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.models.UserEntity;
import ua.foxminded.kucherenko.task3.services.*;

@Controller
@RequestMapping("/administrators")
public class AdministratorsController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private RoleService roleService;

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

    @GetMapping("/manage-roles")
    public String manageRoles(Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<UserEntity> userEntityPage = userService.getAllUsers(page, size);

        model.addAttribute("users", userEntityPage.getContent());
        model.addAttribute("roles", roleService.getAllRoles());

        model.addAttribute("pageNo", page + 1);
        model.addAttribute("totalPages", userEntityPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "admin/manage_roles";
    }
}
