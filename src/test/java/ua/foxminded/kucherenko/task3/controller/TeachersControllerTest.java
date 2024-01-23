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
import ua.foxminded.kucherenko.task3.models.*;
import ua.foxminded.kucherenko.task3.services.*;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TeachersController.class)
public class TeachersControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    @WithMockUser(username = "teacher", authorities = "TEACHER")
    public void shouldReturnOneTeacherView() throws Exception{
        final List<Teacher> testTeachersList = List.of(new Teacher());
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Teacher> testTeachersPage = new PageImpl<>(testTeachersList, pageRequest, testTeachersList.size());

        when(teacherService.getAllTeachers(0,10)).thenReturn(testTeachersPage);

        mvc
                .perform(MockMvcRequestBuilders.get("/teachers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/teachers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"))
                .andExpect(MockMvcResultMatchers.model().attribute("teachers", testTeachersList));
    }
}
