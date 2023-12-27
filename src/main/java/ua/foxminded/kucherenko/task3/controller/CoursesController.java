package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.services.CourseService;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String courses(Model model,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Course> coursesPage = courseService.getAllCourses(page, size);
        model.addAttribute("courses", coursesPage.getContent());
        return "show_pages/courses";
    }
}
