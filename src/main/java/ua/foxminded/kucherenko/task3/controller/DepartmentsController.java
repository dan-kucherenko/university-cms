package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.services.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String departments(Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Department> departmentsPage = departmentService.getAllDepartments(page, size);
        model.addAttribute("departments", departmentsPage.getContent());
        return "show_pages/departments";
    }
}
