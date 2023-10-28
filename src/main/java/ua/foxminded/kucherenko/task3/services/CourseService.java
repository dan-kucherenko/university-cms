package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAllCourses() {
        LOGGER.debug("Getting all the courses");
        return repository.findAll();
    }

    public Optional<Course> getCourseById(int courseId) {
        if (courseId < 1) {
            throw new IllegalArgumentException("Course id can't be less than 1");
        }
        LOGGER.debug("Looking for a course by id");
        return repository.findById(courseId);
    }

    public List<Integer> getAllCourseIds() {
        LOGGER.debug("Getting all the course ids");
        return repository.getAllCourseIds();
    }

    public void saveCourse(Course course) {
        LOGGER.debug("New course is saved");
        repository.save(course);
    }

    public void updateCourse(int courseId, Course updatedCourse) {
        if (courseId < 1) {
            throw new IllegalArgumentException("Error in course id: id can't be less than 1");
        }

        Optional<Course> existingCourseOptional = repository.findById(courseId);
        existingCourseOptional.orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        Course existingCourse = existingCourseOptional.get();
        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setCourseDescription(updatedCourse.getCourseDescription());
        existingCourse.setDepartment(updatedCourse.getDepartment());
        existingCourse.setStudents(updatedCourse.getStudents());

        repository.save(existingCourse);

        LOGGER.debug("Course with ID {} has been updated", courseId);
    }

    public void deleteCourse(int courseId) {
        if (courseId < 1) {
            throw new IllegalArgumentException("Error in course id: id can't be negative");
        }
        LOGGER.debug("Course has been deleted by id");
        repository.deleteById(courseId);
    }
}
