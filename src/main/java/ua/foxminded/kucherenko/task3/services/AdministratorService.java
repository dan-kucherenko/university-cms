package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.models.UserEntity;
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

    public Page<Administrator> getAllAdmins(int page, int size) {
        LOGGER.debug("Getting all the administrators");
        final Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Optional<Administrator> getAdministratorsById(long adminId) {
        if (adminId < 1) {
            throw new IllegalArgumentException("Admin id can't be negative or zero");
        }

        LOGGER.debug("Getting the administrator by id");
        return repository.findById(adminId);
    }

    public void saveAdministrator(UserEntity user) {
        LOGGER.debug("Saving the administrator");
        repository.saveAdministratorFromUser(user);
    }

    public void updateAdminData(long adminId, Administrator admin) {
        if (adminId < 1) {
            throw new IllegalArgumentException("Administrator id can't be negative or zero");
        }

        Administrator foundAdministrator = repository.findById(adminId).orElseThrow(() -> new IllegalArgumentException("Administrator with the given id doesn't exist"));

        foundAdministrator.setFirstName(admin.getFirstName());
        foundAdministrator.setLastName(admin.getLastName());
        foundAdministrator.setEmail(admin.getEmail());
        foundAdministrator.setPhone(admin.getPhone());

        repository.save(foundAdministrator);
        LOGGER.debug("Administrator with id {} has been updated", adminId);
    }

    public void deleteAdministrator(long adminId) {
        if (adminId < 1) {
            throw new IllegalArgumentException("Administrator id can't be negative or zero");
        }
        repository.deleteById(adminId);
    }

    public void deleteAdministratorByUserId(long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("Administrator id can't be negative or zero");
        }
        repository.deleteAdministratorByUserId(userId);
    }
}
