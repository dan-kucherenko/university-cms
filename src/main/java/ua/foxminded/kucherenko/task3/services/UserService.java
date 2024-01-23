package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.dto.RegUserDto;
import ua.foxminded.kucherenko.task3.models.*;
import ua.foxminded.kucherenko.task3.repositories.UserRepository;

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
    private PasswordEncoder passwordEncoder;

    public Page<UserEntity> getAllUsers(int page, int size) {
        LOGGER.debug("Getting all the users");
        final Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public void updateUserRole(long userId, Role role) {
        if (userId < 1) {
            throw new IllegalArgumentException("User id can't be negative or zero");
        }
        UserEntity foundUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with the given id doesn't exist"));
        final Role prevRole = foundUser.getRole();
        userRepository.updateUserRoleById(userId, role);
        foundUser.setRole(role);
        final String newUserRoleName = role.getName();
        addUserToNewRoleTable(newUserRoleName, foundUser);
        if (prevRole != null) {
            final String foundUserRoleName = prevRole.getName();
            deleteUserFromPrevRoleTable(foundUserRoleName, userId);
        }
    }

    public void saveUser(RegUserDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPhone(registrationDto.getPhone());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userRepository.save(user);
    }

    public boolean existsByEmailOrUsername(String username, String email) {
        return userRepository.existsByEmailOrUsername(email, username);
    }

    private void deleteUserFromPrevRoleTable(String foundUserRoleName, long userId) {
        switch (foundUserRoleName) {
            case "ADMIN", "SUPERADMIN" -> administratorService.deleteAdministratorByUserId(userId);
            case "STUDENT" -> studentService.deleteStudentByUserId(userId);
            case "TEACHER" -> teacherService.deleteTeacherByUserId(userId);
            case "USER" -> {
            }
            default -> LOGGER.debug("Error deleting user in other tables");
        }
    }

    private void addUserToNewRoleTable(String newUserRoleName, UserEntity foundUser) {
        switch (newUserRoleName) {
            case "ADMIN", "SUPERADMIN" -> administratorService.saveAdministrator(foundUser);
            case "STUDENT" -> studentService.saveStudent(new Student(foundUser));
            case "TEACHER" -> teacherService.saveTeacher(foundUser);
            default -> LOGGER.debug("Error deleting user in other tables");
        }
    }
}
