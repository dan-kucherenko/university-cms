package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.kucherenko.task3.controller.utility.UpdateRoleResponse;
import ua.foxminded.kucherenko.task3.models.Role;
import ua.foxminded.kucherenko.task3.services.RoleService;
import ua.foxminded.kucherenko.task3.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RoleService roleService;

    @PatchMapping("/update-role")
    public void updateStudent(@RequestBody UpdateRoleResponse response) {
        final int id = response.getId();
        final int roleId = response.getRoleId();

        final Role existingRole = roleService.getRoleById(roleId);
        studentService.updateStudentRole(id, existingRole);
    }
}
