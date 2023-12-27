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
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.services.AdministratorService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(AdministratorsController.class)
public class AdministratorsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdministratorService administratorService;

    @Test
    public void shouldReturnOneAdminView() throws Exception {
        final List<Administrator> testAdminsList = List.of(new Administrator(1, "Super", "Admin", "super.admin@example.com", "999-888-7777"));

        when(administratorService.getAllAdmins()).thenReturn(testAdminsList);

        mvc
                .perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/admins"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("admins"))
                .andExpect(MockMvcResultMatchers.model().attribute("admins", testAdminsList));
    }
}
