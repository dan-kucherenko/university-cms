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
        if (course.getCourseId() <= 0) {
            throw new IllegalArgumentException("Invalid course id: it should be more than 0");
        }
        List<Integer> studentIds = studentRepository.getIdByName(student.getFirstName(), student.getLastName());
        Integer studentId = studentIds.isEmpty() ? null : studentIds.get(0);
        if (studentId == null) {
            throw new IllegalArgumentException("Invalid student id: student id is less than 0 or student doesn't exist");
        }
        if (studentCourseRepository.exists(studentId, course.getCourseId())) {
            throw new IllegalArgumentException("This record already exists");
        }
        studentCourseRepository.addStudentToCourse(studentId, course.getCourseId());
        LOGGER.debug("Student with id {} was successfully added to course {}", studentId, course.getCourseId());
    }

    public void removeStudentFromCourse(Student student, Course course) {
        if (course.getCourseId() <= 0 || course.getCourseId() > 10) {
            throw new IllegalArgumentException("Course Id should be between 1 and 10");
        }
        List<Integer> studentIds = studentRepository.getIdByName(student.getFirstName(), student.getLastName());
        if (studentIds.isEmpty()) {
            throw new IllegalArgumentException("Student doesn't exist or the studentId is incorrect");
        }
        Integer studentId = studentIds.get(0);
        if (!studentCourseRepository.exists(studentId, course.getCourseId())) {
            throw new IllegalArgumentException("This record doesn't exist");
        }
        studentCourseRepository.removeStudentFromCourse(studentId, course.getCourseId());
        LOGGER.debug("Student with id {} was successfully removed from course {}", studentId, course.getCourseId());
    }

    public boolean exists(int studentId, int courseId) {
        if(studentId < 1){
            throw new IllegalArgumentException("Student id cant be negative or less than zero");
        } else if (courseId < 1) {
            throw new IllegalArgumentException("Course id cant be negative or less than zero");
        }
        LOGGER.debug("Checking the existence of student_course with student_id {} and course_id {}", studentId, courseId);
        return studentCourseRepository.exists(studentId, courseId);
    }
}
