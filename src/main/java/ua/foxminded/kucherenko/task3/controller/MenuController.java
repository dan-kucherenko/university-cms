package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.foxminded.kucherenko.task3.services.*;

@Controller
public class MenuController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/administrators")
    public String admins(Model model) {
        model.addAttribute("admins", administratorService.getAllAdmins());
        return "show_pages/admins";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "show_pages/courses";
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "show_pages/departments";
    }

    @GetMapping("/groups")
    public String groups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "show_pages/groups";
    }

    @GetMapping("/lessons")
    public String lessons(Model model) {
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "show_pages/lessons";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "show_pages/students";
    }

    @GetMapping("/teachers")
    public String teachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "show_pages/teachers";
    }
}
