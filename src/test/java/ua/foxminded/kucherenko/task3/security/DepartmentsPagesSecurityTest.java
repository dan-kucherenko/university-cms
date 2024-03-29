package ua.foxminded.kucherenko.task3.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.services.DepartmentService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentsPagesSecurityTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DepartmentService departmentService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testDepartmentsEndpointAccessForAdmin() throws Exception {
        Page<Department> mockPage = new PageImpl<>(Collections.emptyList());
        when(departmentService.getAllDepartments(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/departments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departments"));
    }

    @Test
    @WithMockUser(username = "superadmin", authorities = {"SUPERADMIN"})
    public void testDepartmentsEndpointAccessForSuperAdmin() throws Exception {
        Page<Department> mockPage = new PageImpl<>(Collections.emptyList());
        when(departmentService.getAllDepartments(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/departments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departments"));
    }

    @Test
    @WithMockUser(username = "teacher", authorities = {"TEACHER"})
    public void testDepartmentsEndpointAccessForTeacher() throws Exception {
        Page<Department> mockPage = new PageImpl<>(Collections.emptyList());
        when(departmentService.getAllDepartments(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/departments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departments"));
    }

    @Test
    @WithMockUser(username = "student", authorities = {"STUDENT"})
    public void testDepartmentsEndpointAccessForStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "unauthorized", authorities = {"SOME_ROLE"})
    public void testDepartmentsEndpointAccessForUnauthorizedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}
