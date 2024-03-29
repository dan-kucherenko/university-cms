package ua.foxminded.kucherenko.task3.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.kucherenko.task3.dto.RegUserDto;
import ua.foxminded.kucherenko.task3.services.UserService;


@Controller
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        LOGGER.debug("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication());
        return "login";
    }

    @GetMapping("/login-error")
    public String loginErrorPage(Model model) {
        LOGGER.debug("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("error", "Invalid username or password. Please try again.");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        LOGGER.debug("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("user", new RegUserDto());
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegUserDto user,
                           BindingResult result,
                           RedirectAttributes redirectAttributes, Model model) {
        if (userService.existsByEmailOrUsername(user.getUsername(), user.getEmail())) {
            redirectAttributes.addAttribute("fail", true);
            return "redirect:/register";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        redirectAttributes.addAttribute("success", true);
        return "redirect:/home";
    }
}
