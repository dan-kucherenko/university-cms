package ua.foxminded.kucherenko.task3.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.kucherenko.task3.models.Group;
import ua.foxminded.kucherenko.task3.services.GroupService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupsController.class)
public class GroupsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroupService groupService;

    @Test
    @WithMockUser(username = "student", authorities = {"STUDENT"})
    public void shouldReturnOneGroupView() throws Exception {
        final List<Group> testGroupsList = List.of(new Group(1, "TestGroup", "TestFaculty", "TestSpeciality"));
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Group> testGroupsPage = new PageImpl<>(testGroupsList, pageRequest, testGroupsList.size());
        when(groupService.getAllGroups(0,10)).thenReturn(testGroupsPage);

        mvc
                .perform(MockMvcRequestBuilders.get("/groups"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", testGroupsList))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", testGroupsList));
    }
}
