package ua.foxminded.kucherenko.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
