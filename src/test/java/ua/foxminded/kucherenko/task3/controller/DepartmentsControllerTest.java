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
    @WithMockUser(username = "teacher", authorities = "TEACHER")
    public void shouldReturnOneDepartmentView() throws Exception {
        final List<Department> testDepartmentsList = List.of(new Department(1, "TestDepName", "TestSphere"));
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Department> testDepartmentsPage = new PageImpl<>(testDepartmentsList, pageRequest, testDepartmentsList.size());

        when(departmentService.getAllDepartments(0,10)).thenReturn(testDepartmentsPage);

        mvc
                .perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/departments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departments"))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", testDepartmentsList));
    }
}
