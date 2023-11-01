package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Teacher> getAllTeachers() {
        LOGGER.debug("Getting all the teachers");
        return repository.findAll();
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
        LOGGER.debug("Getting the teacher by department");
        return repository.save(teacher);
    }

    public void updateTeacher(int teacherId, Teacher updatedTeacher) {
        if (teacherId < 1) {
            throw new IllegalArgumentException("Teacher id can't be negative or zero");
        }

        Optional<Teacher> existingTeacherOptional = repository.findById(teacherId);
        existingTeacherOptional.orElseThrow(() -> new IllegalArgumentException("Teacher with given id wasn't found"));

        Teacher existingTeacher = existingTeacherOptional.get();
        existingTeacher.setFirstName(updatedTeacher.getFirstName());
        existingTeacher.setLastName(updatedTeacher.getLastName());
        existingTeacher.setAge(updatedTeacher.getAge());
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
