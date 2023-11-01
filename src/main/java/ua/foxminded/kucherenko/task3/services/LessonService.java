package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Lesson;
import ua.foxminded.kucherenko.task3.repositories.LessonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonService.class);
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getAllLessons() {
        LOGGER.debug("Getting all the lessons");
        return lessonRepository.findAll();
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

        Optional<Lesson> existingLessonOptional = lessonRepository.findById(lessonId);
        existingLessonOptional.orElseThrow(() -> new IllegalArgumentException("Lesson not found with ID: " + lessonId));

        Lesson existingLesson = existingLessonOptional.get();
        existingLesson.setGroup(updatedLesson.getGroup());
        existingLesson.setDayOfWeek(updatedLesson.getDayOfWeek());
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