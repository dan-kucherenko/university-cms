package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.repositories.DepartmentRepository;

@Service
public class DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Page<Department> getAllDepartments(int page, int size) {
        LOGGER.debug("Getting all the departments");
        final Pageable pageable = PageRequest.of(page, size);
        return departmentRepository.findAll(pageable);
    }
}
