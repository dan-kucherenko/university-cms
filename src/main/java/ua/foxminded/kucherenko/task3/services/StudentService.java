package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.List;
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

    public Optional<Student> getStudentById(int studentId) {
        if (studentId < 1) {
            throw new IllegalArgumentException("Student id can't be negative or zero");
        }

        LOGGER.debug("Getting student by id");
        return studentRepository.findById(studentId);
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
        if (student.getAge() < 0) {
            throw new IllegalArgumentException("Student can't have negative age");
        } else if (student.getYearOfStudy() < 0) {
            throw new IllegalArgumentException("Student can't have negative year of study");
        }

        LOGGER.debug("New student has been saved");
        return studentRepository.save(student);
    }

    public void updateStudent(int studentId, Student updatedStudent) {
        if (studentId < 1) {
            throw new IllegalArgumentException("Student id can't be negative or zero");
        }

        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);
        existingStudentOptional.orElseThrow(() -> new IllegalArgumentException("Student with given id wasn't found"));

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
    }

    public void deleteStudent(int studentId) {
        if (studentId < 1) {
            throw new IllegalArgumentException("Student id can't be negative or zero");
        }

        LOGGER.debug("Student was deleted");
        studentRepository.deleteById(studentId);
    }
}
