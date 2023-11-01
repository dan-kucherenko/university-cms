package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.kucherenko.task3.models.GroupStudentsInfo;
import ua.foxminded.kucherenko.task3.repositories.GroupRepository;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class GroupServiceTest {
    @MockBean
    private GroupRepository groupRepository;
    @Autowired
    private GroupService groupService;

    @Test
    void getAllGroupIds() {
        final int groupsNumber = 10;
        final List<Integer> expectedGroupIds = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        when(groupRepository.getAllGroupIds()).thenReturn(expectedGroupIds);

        final List<Integer> groupsIds = groupService.getAllGroupIds();

        verify(groupRepository, times(1)).getAllGroupIds();

        Assertions.assertEquals(groupsNumber, groupsIds.size());
        Assertions.assertEquals(expectedGroupIds, groupsIds);
    }

    @Test
    void getGroupQuantity() {
        final int groupId = 2;
        final int expectedStudentsNum = 2;

        when(groupRepository.getGroupQuantity(groupId)).thenReturn(expectedStudentsNum);

        final int actualStudentsNum = groupService.getGroupQuantity(groupId);

        verify(groupRepository, times(1)).getGroupQuantity(groupId);

        Assertions.assertEquals(expectedStudentsNum, actualStudentsNum);
    }

    @Test
    void getGroupQuantity_NegativeId_ShouldThrowException() {
        final int groupId = -2;
        Assertions.assertThrows(IllegalArgumentException.class, () -> groupService.getGroupQuantity(groupId));
    }

    @Test
    void getGroupByStudentNum() {
        final int studentsNum = 2;
        final int expectedGroupsNum = 2;

        when(groupRepository.getGroupByStudentsQuantity(studentsNum)).thenReturn(List.of(
                new GroupStudentsInfo(2, "GroupB", 2),
                new GroupStudentsInfo(1, "GroupA", 2)
        ));

        final List<GroupStudentsInfo> resultingGroups = groupService.getGroupsByStudentNum(studentsNum);

        verify(groupRepository, times(1)).getGroupByStudentsQuantity(studentsNum);

        Assertions.assertEquals(expectedGroupsNum, resultingGroups.size());
    }

    @Test
    void getGroupByStudentsNum_NegativeQuantity_ShouldThrowException() {
        final int studentNum = -2;
        Assertions.assertThrows(IllegalArgumentException.class, () -> groupService.getGroupsByStudentNum(studentNum));
    }
}
