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
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.services.DepartmentService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentsController.class)
public class DepartmentsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    public void shouldReturnOneDepartmentView() throws Exception {
        final List<Department> testDepartmentsList = List.of(new Department(1, "TestDepName", "TestSphere"));

        when(departmentService.getAllDepartments()).thenReturn(testDepartmentsList);

        mvc
                .perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/departments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departments"))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", testDepartmentsList));
    }
}
