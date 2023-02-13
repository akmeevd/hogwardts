package ru.hogwardts.school;

import org.json.JSONException;
import org.json.JSONObject;
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
import ru.hogwardts.school.controller.FacultyController;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.repository.FacultyRepository;
import ru.hogwardts.school.service.FacultyService;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;


    @Test
    public void createFaculty() throws Exception {
        String name = "griffindor";
        String color = "orange";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("color", color);
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        Mockito.when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders.
                post("/faculty").
                content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
