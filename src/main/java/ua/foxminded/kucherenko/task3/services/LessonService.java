package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Lesson;
import ua.foxminded.kucherenko.task3.repositories.LessonRepository;

import java.util.Optional;

@Service
public class LessonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonService.class);
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Page<Lesson> getAllLessons(int page, int size) {
        LOGGER.debug("Getting all the lessons");
        final Pageable pageable = PageRequest.of(page, size);
        return lessonRepository.findAll(pageable);
    }

    public Optional<Lesson> getLessonById(int lessonId) {
        if (lessonId < 1) {
            throw new IllegalArgumentException("Lesson id can't be negative or zero");
        }

        LOGGER.debug("Getting the lesson by id");
        return lessonRepository.findById(lessonId);
    }

    public void saveLesson(Lesson lesson) {
        LOGGER.debug("New lesson is saved");
        lessonRepository.save(lesson);
    }

    public void updateLesson(int lessonId, Lesson updatedLesson) {
        if (lessonId < 1) {
            throw new IllegalArgumentException("Lesson id can't be negative or zero");
        }

        Optional<Lesson> foundLesson = lessonRepository.findById(lessonId);
        foundLesson.orElseThrow(() -> new IllegalArgumentException("Lesson not found with ID: " + lessonId));

        final Lesson existingLesson = foundLesson.get();
        existingLesson.setGroup(updatedLesson.getGroup());
        existingLesson.setStartTime(updatedLesson.getStartTime());
        existingLesson.setEndTime(updatedLesson.getEndTime());
        existingLesson.setLocation(updatedLesson.getLocation());
        existingLesson.setCourse(updatedLesson.getCourse());

        lessonRepository.save(existingLesson);
        LOGGER.debug("Lesson with ID {} has been updated", lessonId);
    }

    public void deleteLesson(int lessonId) {
        if (lessonId < 1) {
            throw new IllegalArgumentException("Lesson id can't be negative or zero");
        }

        LOGGER.debug("Lesson is deleted");
        lessonRepository.deleteById(lessonId);
    }
}
