package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Teacher;
import ua.foxminded.kucherenko.task3.repositories.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherService.class);
    private final TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Page<Teacher> getAllTeachers(int page, int size) {
        LOGGER.debug("Getting all the teachers");
        final Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Optional<Teacher> getTeacherById(int teacherId) {
        if (teacherId < 1) {
            throw new IllegalArgumentException("Teacher id can't be negative or zero");
        }

        LOGGER.debug("Getting the teacher by id");
        return repository.findById(teacherId);
    }

    public List<Teacher> getTeachersByDepartment(int departmentId) {
        if (departmentId < 1) {
            throw new IllegalArgumentException("Department id can't be negative or zero");
        }
        LOGGER.debug("Getting the teacher by department");
        return repository.getTeachersByDepartmentDepartmentId(departmentId);
    }

    public Teacher saveTeacher(Teacher teacher) {
        LOGGER.debug("Saving the teacher");
        return repository.save(teacher);
    }

    public void updateTeacher(int teacherId, Teacher updatedTeacher) {
        if (teacherId < 1) {
            throw new IllegalArgumentException("Teacher id can't be negative or zero");
        }

        Optional<Teacher> foundTeacher = repository.findById(teacherId);
        foundTeacher.orElseThrow(() -> new IllegalArgumentException("Teacher with given id wasn't found"));

        final Teacher existingTeacher = foundTeacher.get();
        existingTeacher.setFirstName(updatedTeacher.getFirstName());
        existingTeacher.setLastName(updatedTeacher.getLastName());
        existingTeacher.setDateOfBirth(updatedTeacher.getDateOfBirth());
        existingTeacher.setEmail(updatedTeacher.getEmail());
        existingTeacher.setPhone(updatedTeacher.getPhone());
        existingTeacher.setDepartment(updatedTeacher.getDepartment());
        existingTeacher.setSalary(updatedTeacher.getSalary());

        repository.save(existingTeacher);
        LOGGER.debug("Teacher with ID {} has been updated", teacherId);
    }

    public void deleteTeacher(int teacherId) {
        if (teacherId < 1) {
            throw new IllegalArgumentException("Teacher id can't be negative or zero");
        }
        repository.deleteById(teacherId);
    }
}
