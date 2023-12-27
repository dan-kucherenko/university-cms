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
import ua.foxminded.kucherenko.task3.models.*;
import ua.foxminded.kucherenko.task3.services.*;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuController.class)
public class MenuControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdministratorService administratorService;
    @MockBean
    private CourseService courseService;
    @MockBean
    private DepartmentService departmentService;
    @MockBean
    private GroupService groupService;
    @MockBean
    private LessonService lessonService;
    @MockBean
    private StudentService studentService;
    @MockBean
    private TeacherService teacherService;

    @Test
    public void shouldReturnOneAdminView() throws Exception{
        final List<Administrator> testAdminsList = List.of(new Administrator(1, "Super", "Admin", "super.admin@example.com", "999-888-7777"));

        when(administratorService.getAllAdmins()).thenReturn(testAdminsList);

        mvc
                .perform(MockMvcRequestBuilders.get("/administrators"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/admins"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("admins"))
                .andExpect(MockMvcResultMatchers.model().attribute("admins", testAdminsList));
    }

    @Test
    public void shouldReturnOneCourseView() throws Exception{
        final List<Course> testCoursesList = List.of(new Course(1, "TestCourse"));

        when(courseService.getAllCourses()).thenReturn(testCoursesList);

        mvc
                .perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/courses"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attribute("courses", testCoursesList));
    }

    @Test
    public void shouldReturnOneDepartmentView() throws Exception{
        final List<Department> testDepartmentsList = List.of(new Department(1, "TestDepName", "TestSphere"));

        when(departmentService.getAllDepartments()).thenReturn(testDepartmentsList);

        mvc
                .perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/departments"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departments"))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", testDepartmentsList));
    }

    @Test
    public void shouldReturnOneGroupView() throws Exception{
        final List<Group> testGroupsList = List.of(new Group(1, "TestGroup", "TestFaculty", "TestSpeciality"));

        when(groupService.getAllGroups()).thenReturn(testGroupsList);

        mvc
                .perform(MockMvcRequestBuilders.get("/groups"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", testGroupsList))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", testGroupsList));
    }

    @Test
    public void shouldReturnOneLessonView() throws Exception{
        final List<Lesson> testLessonsList = List.of(new Lesson());

        when(lessonService.getAllLessons()).thenReturn(testLessonsList);

        mvc
                .perform(MockMvcRequestBuilders.get("/lessons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/lessons"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lessons"))
                .andExpect(MockMvcResultMatchers.model().attribute("lessons", testLessonsList));
    }

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

    @Test
    public void shouldReturnOneTeacherView() throws Exception{
        final List<Teacher> testTeachersList = List.of(new Teacher(1, "FirstName", "LastName", "email", "phone", new Department()));

        when(teacherService.getAllTeachers()).thenReturn(testTeachersList);

        mvc
                .perform(MockMvcRequestBuilders.get("/teachers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/teachers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"))
                .andExpect(MockMvcResultMatchers.model().attribute("teachers", testTeachersList));
    }
}
