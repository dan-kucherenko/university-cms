package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.repositories.AdministratorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorService.class);
    private final AdministratorRepository repository;

    @Autowired
    public AdministratorService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public List<Administrator> getAllAdmins() {
        LOGGER.debug("Getting all the administrators");
        return repository.findAll();
    }

    public Optional<Administrator> getAdministratorsById (int adminId) {
        if (adminId < 1) {
            throw new IllegalArgumentException("Admin id can't be negative or zero");
        }

        LOGGER.debug("Getting the administrator by id");
        return repository.findById(adminId);
    }

    public Administrator saveAdministrator(Administrator administrator) {
        LOGGER.debug("Saving the administrator");
        return repository.save(administrator);
    }

    public void updateAdminData(int administratorId, Administrator administrator) {
        if (administratorId < 1) {
            throw new IllegalArgumentException("Administrator id can't be negative or zero");
        }

        Optional<Administrator> foundAdministrator = repository.findById(administratorId);
        foundAdministrator.orElseThrow(() -> new IllegalArgumentException("Administrator with the given id doesn't exist"));

        final Administrator dbAdministrator = foundAdministrator.get();
        dbAdministrator.setFirstName(administrator.getFirstName());
        dbAdministrator.setLastName(administrator.getLastName());
        dbAdministrator.setEmail(administrator.getEmail());
        dbAdministrator.setAge(administrator.getAge());
        dbAdministrator.setPhone(administrator.getPhone());

        repository.save(dbAdministrator);
        LOGGER.debug("Administrator with id {} has been updated", administratorId);
    }

    public void deleteAdministrator(int administratorId) {
        if (administratorId < 1) {
            throw new IllegalArgumentException("Administrator id can't be negative or zero");
        }
        repository.deleteById(administratorId);
    }
}
