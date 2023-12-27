package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.kucherenko.task3.services.CourseService;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "show_pages/courses";
    }
}
