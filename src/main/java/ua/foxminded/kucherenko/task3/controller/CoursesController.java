package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.services.CourseService;
import ua.foxminded.kucherenko.task3.services.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String courses(Model model,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Course> coursesPage = courseService.getAllCourses(page, size);
        model.addAttribute("courses", coursesPage.getContent());
        model.addAttribute("pageNo", page + 1);
        model.addAttribute("totalPages", coursesPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "show_pages/courses";
    }

    @GetMapping("/edit/{courseId}")
    public String edit(Model model, @PathVariable int courseId) {
        final Course course = courseService.getCourseById(courseId).orElseThrow();
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute(course);
        model.addAttribute("departments", departments);
        return "edit/course";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("course", new Course());
        return "create/course";
    }

    @PostMapping("/create")
    public String processCreateForm(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @PatchMapping("/edit")
    public String processEditForm(@RequestBody Course course) {
        courseService.updateCourse(course.getCourseId(), course);
        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/courses";
    }
}
