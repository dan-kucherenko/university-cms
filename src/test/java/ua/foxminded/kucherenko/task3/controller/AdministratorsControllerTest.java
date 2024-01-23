package ua.foxminded.kucherenko.task3.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.models.UserEntity;
import ua.foxminded.kucherenko.task3.services.AdministratorService;
import ua.foxminded.kucherenko.task3.services.UserService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdministratorsControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AdministratorService administratorService;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void shouldReturnOneAdminView() throws Exception {
        final List<Administrator> testAdminsList = List.of(new Administrator());
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Administrator> testAdminsPage = new PageImpl<>(testAdminsList, pageRequest, testAdminsList.size());

        when(administratorService.getAllAdmins(0,10)).thenReturn(testAdminsPage);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/admins"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("admins"))
                .andExpect(MockMvcResultMatchers.model().attribute("admins", testAdminsList));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testAdminsEndpointAccessForAdmin() throws Exception {
        Page<Administrator> mockPage = new PageImpl<>(Collections.emptyList());
        when(administratorService.getAllAdmins(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/admins"));
    }

    @Test
    @WithMockUser(username = "superadmin", authorities = {"SUPERADMIN"})
    public void tesAdminsEndpointAccessForSuperAdmin() throws Exception {
        Page<Administrator> mockPage = new PageImpl<>(Collections.emptyList());
        when(administratorService.getAllAdmins(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/admins"));
    }

    @Test
    @WithMockUser(username = "teacher", authorities = {"TEACHER"})
    public void testAdminsEndpointAccessForTeacher() throws Exception {
        Page<Administrator> mockPage = new PageImpl<>(Collections.emptyList());
        when(administratorService.getAllAdmins(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "student", authorities = {"STUDENT"})
    public void testAdminsEndpointAccessForStudent() throws Exception {
        Page<Administrator> mockPage = new PageImpl<>(Collections.emptyList());
        when(administratorService.getAllAdmins(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "superadmin", authorities = {"SUPERADMIN"})
    public void testManageRolesEndpointAccessForSuperadmin() throws Exception {
        Page<UserEntity> mockPage = new PageImpl<>(Collections.emptyList());
        when(userService.getAllUsers(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators/manage-roles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/manage_roles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testManageRolesEndpointAccessForAdmin() throws Exception {
        Page<UserEntity> mockPage = new PageImpl<>(Collections.emptyList());
        when(userService.getAllUsers(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators/manage-roles"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "teacher", authorities = {"TEACHER"})
    public void testManageRolesEndpointAccessForTeacher() throws Exception {
        Page<UserEntity> mockPage = new PageImpl<>(Collections.emptyList());
        when(userService.getAllUsers(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators/manage-roles"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "student", authorities = {"STUDENT"})
    public void testManageRolesEndpointAccessForStudent() throws Exception {
        Page<UserEntity> mockPage = new PageImpl<>(Collections.emptyList());
        when(userService.getAllUsers(anyInt(), anyInt()))
                .thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/administrators/manage-roles"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
