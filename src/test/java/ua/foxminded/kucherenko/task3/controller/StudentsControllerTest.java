package ua.foxminded.kucherenko.task3.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.services.StudentService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentsController.class)
public class StudentsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldReturnOneStudentView() throws Exception{
        final List<Student> testStudentsList = List.of(new Student(1, "FirstName", "LastName"));

        when(studentService.getAllStudents()).thenReturn(testStudentsList);

        mvc
                .perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/students"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.model().attribute("students", testStudentsList));
    }
}
