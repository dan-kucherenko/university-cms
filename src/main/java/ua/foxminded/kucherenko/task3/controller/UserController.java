package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.kucherenko.task3.controller.utility.UpdateRoleRequest;
import ua.foxminded.kucherenko.task3.models.Role;
import ua.foxminded.kucherenko.task3.services.RoleService;
import ua.foxminded.kucherenko.task3.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PatchMapping("/roles")
    public void updateUser(@RequestBody UpdateRoleRequest request) {
        final int id = request.getId();
        final int roleId = request.getRoleId();

        final Role existingRole = roleService.getRoleById(roleId);
        userService.updateUserRole(id, existingRole);
    }
}
