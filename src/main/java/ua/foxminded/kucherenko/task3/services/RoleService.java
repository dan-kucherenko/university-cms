package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Role;
import ua.foxminded.kucherenko.task3.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getAllRoles() {
        LOGGER.debug("Getting all the roles");
        return repository.findAll();
    }

    public Role getRoleById(int id) {
        return repository.getRoleById(id);
    }

    public Role getRoleByName(String name) {
        return repository.getRoleByName(name);
    }
}
