package ua.foxminded.kucherenko.task3.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

    @Test
    @Sql({"/database/create_tables.sql","/sample_data/courses_samples.sql"})
    void getAllCourseIds() {
        final int coursesIdListSize = 10;
        final List<Integer> coursesIds = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final List<Integer> allSCoursesIds = repository.getAllCourseIds();
        Assertions.assertEquals(coursesIdListSize, allSCoursesIds.size());
        Assertions.assertEquals(coursesIds, allSCoursesIds);
    }
}
