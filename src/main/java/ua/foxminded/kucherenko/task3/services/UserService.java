package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.dto.RegUserDto;
import ua.foxminded.kucherenko.task3.models.*;
import ua.foxminded.kucherenko.task3.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RoleService roleService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public Page<UserEntity> getAllUsers(int page, int size) {
        LOGGER.debug("Getting all the users");
        final Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public void updateUserRole(long userId, Role role) {
        if (userId < 1) {
            throw new IllegalArgumentException("User id can't be negative or zero");
        }
        Optional<UserEntity> foundUser = userRepository.findById(userId);
        foundUser.orElseThrow(() -> new IllegalArgumentException("User with the given id doesn't exist"));
        final Role prevRole = foundUser.get().getRole();
        if (prevRole != null) {
            final String foundUserRoleName = prevRole.getName();
            deleteUserFromPrevRoleTable(foundUserRoleName, userId);
        }
        userRepository.updateUserRoleById(userId, role);
        final String newUserRoleName = role.getName();
        addUserToNewRoleTable(newUserRoleName, foundUser);
    }

    public void saveUser(RegUserDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
//        user.setPassword(passwordEncoder.encode(regixstrationDto.getPassword()));
        Role role = roleService.getRoleByName("USER");
        user.setRole(role);
        userRepository.save(user);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void deleteUserFromPrevRoleTable(String foundUserRoleName, long userId) {
        switch (foundUserRoleName) {
            case "admin", "superadmin" -> administratorService.deleteAdministrator(userId);
            case "student" -> studentService.deleteStudent(userId);
            case "teacher" -> teacherService.deleteTeacher(userId);
            default -> LOGGER.debug("Error deleting user in other tables");
        }
    }

    private void addUserToNewRoleTable(String newUserRoleName, Optional<UserEntity> foundUser) {
        UserEntity user = foundUser.get();
        switch (newUserRoleName) {
            case "admin", "superadmin" -> administratorService.saveAdministrator(new Administrator(user));
            case "student" -> studentService.saveStudent(new Student(user));
            case "teacher" -> teacherService.saveTeacher(new Teacher(user));
            default -> LOGGER.debug("Error deleting user in other tables");
        }
    }
}
