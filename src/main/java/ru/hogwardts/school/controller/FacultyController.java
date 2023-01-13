package ru.hogwardts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService faculties;

    public FacultyController(FacultyService faculties) {
        this.faculties = faculties;
    }

    @GetMapping("{id}")
    public Faculty findStudent(@PathVariable long id) {
        return faculties.findFaculty(id);
    }

    @PostMapping
    public Faculty createStudent(@RequestBody Faculty faculty) {
        return faculties.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editStudent(@RequestBody Faculty faculty) {
        return faculties.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteStudent(@PathVariable long id) {
        return faculties.deleteFaculty(id);
    }

    @GetMapping()
    public Collection<Faculty> findFacultiesByColor(@RequestParam String color) {
        return faculties.findFacultiesByColor(color);
    }
}
