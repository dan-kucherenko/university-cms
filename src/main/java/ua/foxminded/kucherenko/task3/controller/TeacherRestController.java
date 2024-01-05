package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.kucherenko.task3.controller.utility.UpdateRoleResponse;
import ua.foxminded.kucherenko.task3.models.Role;
import ua.foxminded.kucherenko.task3.services.RoleService;
import ua.foxminded.kucherenko.task3.services.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherRestController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RoleService roleService;

    @PatchMapping("/update-role")
    public void updateTeacher(@RequestBody UpdateRoleResponse response) {
        final int id = response.getId();
        final int roleId = response.getRoleId();

        final Role existingRole = roleService.getRoleById(roleId);
        teacherService.updateTeacherRole(id, existingRole);
    }
}
