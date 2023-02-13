package ru.hogwardts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwardts.school.controller.AvatarController;
import ru.hogwardts.school.controller.FacultyController;
import ru.hogwardts.school.controller.StudentController;
import ru.hogwardts.school.model.Student;
import ru.hogwardts.school.repository.AvatarRepository;
import ru.hogwardts.school.repository.StudentRepository;
import ru.hogwardts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarController avatarController;
    @MockBean
    private FacultyController facultyController;

    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    private JSONObject jsonObject;

    @BeforeEach
    public void setUp() {
        String name = "Ivanov Ivan";
        int age = 21;
        long id = 1;
        student = new Student(id, name, age);

        jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("age", age);
    }

    @Test
    public void createStudent() throws Exception {
        Mockito.when(studentRepository.save(any())).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/student", student, Student.class)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    public void getStudent() throws Exception {
        Mockito.when(studentRepository.findById(any())).thenReturn(Optional.ofNullable(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/" + student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    public void editStudent() throws Exception {
        Mockito.when(studentRepository.save(any())).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.put("/student", student, Student.class)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    public void deleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/" + student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findStudentsByAge() throws Exception {
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/student?age=" + student.getAge())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(student.getId()))
                .andExpect(jsonPath("$[0].name").value(student.getName()))
                .andExpect(jsonPath("$[0].age").value(student.getAge()));
    }

    @Test
    public void findStudentsByAgeBetween() throws Exception {
        int minAge = 0;
        int maxAge = 100;
        Mockito.when(studentRepository.findStudentByAgeBetween(anyInt(), anyInt())).thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/students-by-age-between?min=" + minAge + "&max=" + maxAge)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(student.getId()))
                .andExpect(jsonPath("$[0].name").value(student.getName()))
                .andExpect(jsonPath("$[0].age").value(student.getAge()));
    }

    @Test
    public void findStudentsByFacultyId() throws Exception {
        Mockito.when(studentRepository.findStudentsByFaculty_Id(anyLong())).thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/student-by-faculty-id?id=" + student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(student.getId()))
                .andExpect(jsonPath("$[0].name").value(student.getName()))
                .andExpect(jsonPath("$[0].age").value(student.getAge()));
    }

}


