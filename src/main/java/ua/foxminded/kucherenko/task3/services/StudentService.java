package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        LOGGER.debug("Getting all students");
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        LOGGER.debug("Getting student by id");
        return studentRepository.findById(id);
    }

    public List<Integer> getAllStudentIds() {
        LOGGER.debug("Getting all student ids");
        return studentRepository.getAllStudentIds();
    }

    public List<Student> getStudentsByCourse(String courseName) {
        LOGGER.debug("Getting students by course");
        return studentRepository.getByCourse(courseName);
    }

    public List<Integer> getStudentIdsByName(String firstName, String lastName) {
        LOGGER.debug("Getting student id by name");
        return studentRepository.getIdByName(firstName, lastName);
    }

    public Student saveStudent(Student student) {
        LOGGER.debug("New student has been saved");
        return studentRepository.save(student);
    }

    public void updateStudent(Integer studentId, Student updatedStudent) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setDob(updatedStudent.getDob());
            existingStudent.setAge(updatedStudent.getAge());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setPhone(updatedStudent.getPhone());
            existingStudent.setYearOfStudy(updatedStudent.getYearOfStudy());

            studentRepository.save(existingStudent);

            LOGGER.debug("Student with ID {} has been updated", studentId);
        } else {
            LOGGER.error("Student not found with ID: {}", studentId);
            throw new NoSuchElementException("Student not found with ID: " + studentId);
        }
    }

    public void deleteStudent(int id) {
        LOGGER.debug("Student was deleted");
        studentRepository.deleteById(id);
    }
}
