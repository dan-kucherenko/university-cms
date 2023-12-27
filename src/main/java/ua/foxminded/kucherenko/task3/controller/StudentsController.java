package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.kucherenko.task3.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "show_pages/students";
    }
}
