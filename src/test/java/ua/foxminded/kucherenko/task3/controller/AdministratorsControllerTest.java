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
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.services.AdministratorService;
import ua.foxminded.kucherenko.task3.services.RoleService;
import ua.foxminded.kucherenko.task3.services.UserService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(AdministratorsController.class)
public class AdministratorsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdministratorService administratorService;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void shouldReturnOneAdminView() throws Exception {
        final List<Administrator> testAdminsList = List.of(new Administrator());
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Administrator> testAdminsPage = new PageImpl<>(testAdminsList, pageRequest, testAdminsList.size());

        when(administratorService.getAllAdmins(0,10)).thenReturn(testAdminsPage);

        mvc
                .perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/admins"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("admins"))
                .andExpect(MockMvcResultMatchers.model().attribute("admins", testAdminsList));
    }
}
