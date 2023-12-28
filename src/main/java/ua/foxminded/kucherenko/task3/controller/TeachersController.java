package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.kucherenko.task3.models.Teacher;
import ua.foxminded.kucherenko.task3.services.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeachersController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String teachers(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Teacher> teachersPage = teacherService.getAllTeachers(page, size);
        model.addAttribute("teachers", teachersPage.getContent());
        model.addAttribute("pageNo", page + 1);
        model.addAttribute("totalPages", teachersPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "show_pages/teachers";
    }
}
