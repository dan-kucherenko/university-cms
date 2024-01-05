package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.models.Teacher;
import ua.foxminded.kucherenko.task3.services.AdministratorService;
import ua.foxminded.kucherenko.task3.services.RoleService;
import ua.foxminded.kucherenko.task3.services.StudentService;
import ua.foxminded.kucherenko.task3.services.TeacherService;

@Controller
@RequestMapping("/administrators")
public class AdministratorsController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
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
        final Page<Student> studentPage = studentService.getAllStudents(page, size);
        final Page<Administrator> administratorPage = administratorService.getAllAdmins(page, size);
        final Page<Teacher> teacherPage = teacherService.getAllTeachers(page, size);

        model.addAttribute("admins", administratorPage.getContent());
        model.addAttribute("teachers", teacherPage.getContent());
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("roles", roleService.getAllRoles());

        model.addAttribute("pageNo", page + 1);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "admin/manage_roles";
    }
}
