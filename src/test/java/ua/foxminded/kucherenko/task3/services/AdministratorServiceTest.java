package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.repositories.AdministratorRepository;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdministratorServiceTest {
    @MockBean
    private AdministratorRepository repository;
    @Autowired
    private AdministratorService administratorService;

    @Test
    void getAdministratorsById() {
        int adminId = 1;
        final Administrator administrator = new Administrator();
        when(repository.findById(adminId)).thenReturn(Optional.of(administrator));
        final Optional<Administrator> result = administratorService.getAdministratorsById(adminId);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(administrator, result.get());
        verify(repository).findById(adminId);
    }

    @Test
    void saveAdministrator() {
        final Administrator mockAdministrator = new Administrator();
        when(repository.save(mockAdministrator)).thenReturn(mockAdministrator);
        final Administrator result = administratorService.saveAdministrator(mockAdministrator);

        Assertions.assertEquals(mockAdministrator, result);
        verify(repository).save(mockAdministrator);
    }

    @Test
    void getAdministratorById_TeacherId_ShouldThrowException() {
        final int administratorId = -4;
        Assertions.assertThrows(IllegalArgumentException.class, () -> administratorService.getAdministratorsById(administratorId));
    }

    @Test
    void updateAdminData() {
        final int adminId = 1;
        final Administrator existingAdmin = new Administrator("Test1", "Test1");
        final Administrator updatedAdmin = new Administrator("Test2", "Test2");
        when(repository.findById(adminId)).thenReturn(Optional.of(existingAdmin));

        administratorService.updateAdminData(adminId, updatedAdmin);

        verify(repository).findById(adminId);
        verify(repository).save(existingAdmin);
        Assertions.assertEquals(updatedAdmin.getFirstName(), existingAdmin.getFirstName());
        Assertions.assertEquals(updatedAdmin.getLastName(), existingAdmin.getLastName());
        Assertions.assertEquals(updatedAdmin.getEmail(), existingAdmin.getEmail());
        Assertions.assertEquals(updatedAdmin.getPhone(), existingAdmin.getPhone());
    }

    @Test
    void updateAdministrator_NegativeTeacherId_ShouldThrowException() {
        final int administratorId = -1;
        final Administrator administrator = new Administrator();
        Assertions.assertThrows(IllegalArgumentException.class, () -> administratorService.updateAdminData(administratorId, administrator));
    }

    @Test
    void deleteAdministrator() {
        final int adminId = 1;
        administratorService.deleteAdministrator(adminId);
        verify(repository).deleteById(adminId);
    }

    @Test
    void deleteAdministrator_NegativeTeacherId_ShouldThrowException() {
        final int administratorId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> administratorService.deleteAdministrator(administratorId));
    }
}
