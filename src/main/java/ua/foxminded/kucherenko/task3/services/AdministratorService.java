package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.repositories.AdministratorRepository;

import java.util.Optional;

@Service
public class AdministratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorService.class);
    private final AdministratorRepository repository;

    @Autowired
    public AdministratorService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public Page<Administrator> getAllAdmins(int page, int size) {
        LOGGER.debug("Getting all the administrators");
        final Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Optional<Administrator> getAdministratorsById (int adminId) {
        if (adminId < 1) {
            throw new IllegalArgumentException("Admin id can't be negative or zero");
        }

        LOGGER.debug("Getting the administrator by id");
        return repository.findById(adminId);
    }

    public Administrator saveAdministrator(Administrator admin) {
        LOGGER.debug("Saving the administrator");
        return repository.save(admin);
    }

    public void updateAdminData(int adminId, Administrator admin) {
        if (adminId < 1) {
            throw new IllegalArgumentException("Administrator id can't be negative or zero");
        }

        Optional<Administrator> foundAdministrator = repository.findById(adminId);
        foundAdministrator.orElseThrow(() -> new IllegalArgumentException("Administrator with the given id doesn't exist"));

        final Administrator existingAdmin = foundAdministrator.get();
        existingAdmin.setFirstName(admin.getFirstName());
        existingAdmin.setLastName(admin.getLastName());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPhone(admin.getPhone());

        repository.save(existingAdmin);
        LOGGER.debug("Administrator with id {} has been updated", adminId);
    }

    public void deleteAdministrator(int adminId) {
        if (adminId < 1) {
            throw new IllegalArgumentException("Administrator id can't be negative or zero");
        }
        repository.deleteById(adminId);
    }
}
