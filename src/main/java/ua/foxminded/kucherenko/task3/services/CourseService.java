package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.CourseRepository;
import ua.foxminded.kucherenko.task3.repositories.StudentCourseRepository;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, StudentCourseRepository studentCourseRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public Page<Course> getAllCourses(int page, int size) {
        LOGGER.debug("Getting all the courses");
        final Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable);
    }

    public List<Course> getAllCourses() {
        LOGGER.debug("Getting all the courses");
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int courseId) {
        if (courseId < 1) {
            throw new IllegalArgumentException("Course id can't be less than 1");
        }
        LOGGER.debug("Looking for a course by id");
        return courseRepository.findById(courseId);
    }

    public void saveCourse(Course course) {
        LOGGER.debug("New course is saved");
        courseRepository.save(course);
    }

    public void updateCourse(int courseId, Course updatedCourse) {
        if (courseId < 1) {
            throw new IllegalArgumentException("Error in course id: id can't be less than 1");
        }

        Optional<Course> foundCourse = courseRepository.findById(courseId);
        foundCourse.orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        final Course existingCourse = foundCourse.get();
        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setCourseDescription(updatedCourse.getCourseDescription());
        existingCourse.setDepartment(updatedCourse.getDepartment());
        existingCourse.setStudents(updatedCourse.getStudents());

        courseRepository.save(existingCourse);
        LOGGER.debug("Course with ID {} has been updated", courseId);
    }

    public void deleteCourse(int courseId) {
        if (courseId < 1) {
            throw new IllegalArgumentException("Error in course id: id can't be negative");
        }
        LOGGER.debug("Course has been deleted by id");
        courseRepository.deleteById(courseId);
    }

    public void addStudentToCourse(Student student, Course course) {
        if (course.getCourseId() <= 0) {
            throw new IllegalArgumentException("Invalid course id: it should be more than 0");
        }
        Optional<Student> foundStudent = studentRepository.findById(student.getId());
        foundStudent.orElseThrow(() -> new IllegalArgumentException("Invalid student id: student id is less than 0 or student doesn't exist"));

        Optional<Course> dbCourse = courseRepository.findById(course.getCourseId());
        dbCourse.orElseThrow(() -> new IllegalArgumentException("Invalid course id: course id is less than 0 or course doesn't exist"));

        if (studentCourseRepository.exists(foundStudent.get().getId(), course.getCourseId())) {
            throw new IllegalArgumentException("This record already exists");
        }
        studentCourseRepository.addStudentToCourse(foundStudent.get().getId(), course.getCourseId());
        LOGGER.debug("Student with id {} was successfully added to course {}", foundStudent.get().getId(), course.getCourseId());
    }

    public void removeStudentFromCourse(Student student, Course course) {
        if (course.getCourseId() <= 0) {
            throw new IllegalArgumentException("Course Id should be more then 0 or 0");
        }
        Optional<Student> foundStudent = studentRepository.findById(student.getId());
        foundStudent.orElseThrow(() -> new IllegalArgumentException("Invalid student id: student id is less than 0 or student doesn't exist"));

        Long studentId = foundStudent.get().getId();
        if (!studentCourseRepository.exists(studentId, course.getCourseId())) {
            throw new IllegalArgumentException("This record doesn't exist");
        }
        studentCourseRepository.removeStudentFromCourse(studentId, course.getCourseId());
        LOGGER.debug("Student with id {} was successfully removed from course {}", studentId, course.getCourseId());
    }
}
