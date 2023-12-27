package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.kucherenko.task3.services.TeacherService;

@Controller
@RequestMapping("/teachers") public class TeachersController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String teachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "show_pages/teachers";
    }
}
