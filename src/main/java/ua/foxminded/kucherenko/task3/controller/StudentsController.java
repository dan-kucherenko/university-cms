package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String students(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Student> studentsPage = studentService.getAllStudents(page, size);
        model.addAttribute("students", studentsPage.getContent());
        model.addAttribute("pageNo", page + 1);
        model.addAttribute("totalPages", studentsPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "show_pages/students";
    }
}
