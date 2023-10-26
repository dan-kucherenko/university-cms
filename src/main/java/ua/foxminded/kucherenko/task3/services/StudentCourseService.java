package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.CourseRepository;
import ua.foxminded.kucherenko.task3.repositories.StudentCourseRepository;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentCourseService.class);

    @Autowired
    public StudentCourseService(StudentRepository studentRepository,
                                StudentCourseRepository studentCourseRepository,
                                CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
    }

    public void addStudentToCourse(Student student, Course course) {
        if (course.getId() <= 0) {
            throw new IllegalArgumentException("Invalid course id: it should be more than 0");
        }
        List<Integer> studentIds = studentRepository.getIdByName(student.getFirstName(), student.getLastName());
        Integer studentId = studentIds.isEmpty() ? null : studentIds.get(0);
        if (studentId == null) {
            throw new IllegalArgumentException("Invalid student id: student id is less than 0 or student doesn't exist");
        }
        if (studentCourseRepository.exists(studentId, course.getId())) {
            throw new IllegalArgumentException("This record already exists");
        }
        save(studentId, course.getId());
        LOGGER.debug("Student with id {} was successfully added to course {}", studentId, course.getId());
    }

    public void removeStudentFromCourse(Student student, Course course) {
        if (course.getId() <= 0 || course.getId() > 10) {
            throw new IllegalArgumentException("Course Id should be between 1 and 10");
        }
        List<Integer> studentIds = studentRepository.getIdByName(student.getFirstName(), student.getLastName());
        if (studentIds.isEmpty()) {
            throw new IllegalArgumentException("Student doesn't exist or the studentId is incorrect");
        }
        Integer studentId = studentIds.get(0);

        delete(studentId, course.getId());
        LOGGER.debug("Student with id {} was successfully removed from course {}", studentId, course.getId());
    }

    private void save(int studentId, int courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.orElseThrow(() -> new IllegalArgumentException("Student with given id wasn't found"));
        Optional<Course> course = courseRepository.findById(courseId);
        course.orElseThrow(() -> new IllegalArgumentException("Course with given id wasn't found"));

        student.get().getCourses().add(course.get());
        studentRepository.save(student.get());
    }

    private void delete(int studentId, int courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Student existingStudent = student.orElseThrow(() -> new IllegalArgumentException("Student with given id wasn't found"));
        Optional<Course> course = courseRepository.findById(courseId);
        Course existingCourse = course.orElseThrow(() -> new IllegalArgumentException("Course with given id wasn't found"));

        existingStudent.getCourses().remove(existingCourse);
        studentRepository.save(existingStudent);
    }
}
