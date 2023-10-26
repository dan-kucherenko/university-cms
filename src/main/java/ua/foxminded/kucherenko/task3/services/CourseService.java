package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.repositories.CourseRepository;

import java.util.List;
import java.util.NoSuchElementException;
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

    public Optional<Course> getCourseById(Integer id) {
        LOGGER.debug("Looking for a course by id");
        return repository.findById(id);
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
        Optional<Course> existingCourseOptional = repository.findById(courseId);

        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setDepartment(updatedCourse.getDepartment());
            existingCourse.setStudents(updatedCourse.getStudents());

            repository.save(existingCourse);

            LOGGER.debug("Course with ID {} has been updated", courseId);
        } else {
            LOGGER.error("Course not found with ID: {}", courseId);
            throw new NoSuchElementException("Course not found with ID: " + courseId);
        }
    }

    public void deleteCourse(Integer id) {
        LOGGER.debug("Course has been deleted by id");
        repository.deleteById(id);
    }
}
