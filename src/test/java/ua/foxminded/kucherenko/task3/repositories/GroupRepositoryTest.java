package ua.foxminded.kucherenko.task3.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.kucherenko.task3.models.GroupStudentsInfo;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
class GroupRepositoryTest {
    @Autowired
    private GroupRepository repository;

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql"})
    void getAllGroupIds() {
        final int groupsNumber = 10;
        final Integer[] expectedGroupIds = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final List<Integer> groupsIds = repository.getAllGroupIds();
        Assertions.assertEquals(groupsNumber, groupsIds.size());
        Assertions.assertEquals(Arrays.stream(expectedGroupIds).toList(), groupsIds);
    }

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/students_samples.sql"})
    void getGroupByStudentNum() {
        final int studentsNum = 2;
        final int expectedGroupsNum = 2;
        final List<GroupStudentsInfo> resultingGroups = repository.getGroupByStudentNum(studentsNum);
        final List<GroupStudentsInfo> expectedGroups = List.of(
                new GroupStudentsInfo(2, "GroupB", 2),
                new GroupStudentsInfo(1, "GroupA", 2)
        );
        Assertions.assertEquals(expectedGroupsNum, resultingGroups.size());
        Assertions.assertEquals(expectedGroups, resultingGroups);
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
