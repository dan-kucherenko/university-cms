package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.StudentCourseRepository;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public Page<Student> getAllStudents(int page, int size) {
        LOGGER.debug("Getting all students");
        final Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    public Optional<Student> getStudentById(long studentId) {
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
        if (student.getYearOfStudy() != null && student.getYearOfStudy() < 0) {
            throw new IllegalArgumentException("Student can't have negative year of study");
        }

        LOGGER.debug("New student has been saved");
        return studentRepository.save(student);
    }

    public void updateStudent(long studentId, Student updatedStudent) {
        if (studentId < 1) {
            throw new IllegalArgumentException("Student id can't be negative or zero");
        }

        Optional<Student> foundStudent = studentRepository.findById(studentId);
        foundStudent.orElseThrow(() -> new IllegalArgumentException("Student with given id wasn't found"));

        final Student existingStudent = foundStudent.get();
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setYearOfStudy(updatedStudent.getYearOfStudy());

        studentRepository.save(existingStudent);
        LOGGER.debug("Student with ID {} has been updated", studentId);
    }

    public void deleteStudent(long studentId) {
        if (studentId < 1) {
            throw new IllegalArgumentException("Student id can't be negative or zero");
        }

        LOGGER.debug("Student was deleted");
        studentRepository.deleteById(studentId);
    }

    public boolean exists(long studentId, int courseId) {
        if (studentId < 1) {
            throw new IllegalArgumentException("Student id cant be negative or less than zero");
        } else if (courseId < 1) {
            throw new IllegalArgumentException("Course id cant be negative or less than zero");
        }
        LOGGER.debug("Checking the existence of student_course with student_id {} and course_id {}", studentId, courseId);
        return studentCourseRepository.exists(studentId, courseId);
    }
}
