package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.kucherenko.task3.models.Administrator;

@SpringBootTest
class AdministratorServiceTest {
    @Autowired
    private AdministratorService administratorService;

    @Test
    void getTeacherById_TeacherId_ShouldThrowException() {
        final int administratorId = -4;
        Assertions.assertThrows(IllegalArgumentException.class, () -> administratorService.getAdministratorsById(administratorId));
    }

    @Test
    void updateTeacher_NegativeTeacherId_ShouldThrowException () {
        final int administratorId = -1;
        final Administrator administrator = new Administrator();
        Assertions.assertThrows(IllegalArgumentException.class, () -> administratorService.updateAdminData(administratorId, administrator));
    }

    @Test
    void deleteTeacher_NegativeTeacherId_ShouldThrowException () {
        final int administratorId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> administratorService.deleteAdministrator(administratorId));
    }
}
