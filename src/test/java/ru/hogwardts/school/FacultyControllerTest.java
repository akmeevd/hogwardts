package ru.hogwardts.school;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwardts.school.controller.AvatarController;
import ru.hogwardts.school.controller.FacultyController;
import ru.hogwardts.school.controller.StudentController;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.repository.FacultyRepository;
import ru.hogwardts.school.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private AvatarController avatarController;
    @MockBean
    private StudentController studentController;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;
    private Faculty faculty;
    private JSONObject jsonObject;

    @BeforeEach
    public void setUp() throws JSONException {
        String name = "griffindor";
        String color = "orange";
        Long id = 1L;
        jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("color", color);
        jsonObject.put("id", id);
        faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        faculty.setId(id);
    }


    @Test
    public void createFaculty() throws Exception {
        Mockito.when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders.
                        post("/faculty").
                        content(jsonObject.toString()).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value(faculty.getName())).
                andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andExpect(jsonPath("$.id").value(faculty.getId()));
    }

    @Test
    public void editFaculty() throws Exception {
        Mockito.when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders.
                        put("/faculty").
                        content(jsonObject.toString()).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value(faculty.getName())).
                andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andExpect(jsonPath("$.id").value(faculty.getId()));
    }

    @Test
    public void findFaculty() throws Exception {
        Mockito.when(facultyRepository.findById(any())).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + faculty.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }

    @Test
    public void deleteFaculty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + faculty.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findFacultiesByColor() throws Exception {
        Mockito.when(facultyRepository.findFacultiesByColorIgnoreCase(any())).thenReturn(List.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty?color=" + faculty.getColor()).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].name").value(faculty.getName())).
                andExpect(jsonPath("$[0].color").value(faculty.getColor())).
                andExpect(jsonPath("$[0].id").value(faculty.getId()));
    }
    @Test
    public void findFacultiesByColorOrName() throws Exception {
        Mockito.when(facultyRepository.findFacultiesByColorIgnoreCaseOrNameIgnoreCase(any(), any())).
                thenReturn(List.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/findFacultyByColorOrName?color="
                                + faculty.getColor() +
                                "&name=" + faculty.getName()).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].name").value(faculty.getName())).
                andExpect(jsonPath("$[0].color").value(faculty.getColor())).
                andExpect(jsonPath("$[0].id").value(faculty.getId()));
    }

    @Test
    public void findFacultyByStudent() throws Exception {
        long studentId = 1L;
        Mockito.when(facultyRepository.findById(any())).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/findFacultyByStudent?id=" + studentId)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
