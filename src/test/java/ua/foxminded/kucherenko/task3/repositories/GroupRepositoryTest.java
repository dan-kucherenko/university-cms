package ua.foxminded.kucherenko.task3.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.kucherenko.task3.models.GroupStudentsInfo;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class GroupRepositoryTest {
    @Autowired
    private GroupRepository repository;

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql"})
    void getAllGroupIds() {
        final int groupsNumber = 10;
        final List<Integer> expectedGroupIds = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final List<Integer> groupsIds = repository.getAllGroupIds();
        Assertions.assertEquals(groupsNumber, groupsIds.size());
        Assertions.assertEquals(expectedGroupIds, groupsIds);
    }

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/students_samples.sql"})
    void getGroupQuantity() {
        final int groupId = 2;
        final int expectedStudentsNum = 2;
        final int actualStudentsNum = repository.getGroupQuantity(groupId);
        Assertions.assertEquals(expectedStudentsNum, actualStudentsNum);
    }
}
