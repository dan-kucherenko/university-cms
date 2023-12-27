package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.kucherenko.task3.services.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String departments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "show_pages/departments";
    }
}
