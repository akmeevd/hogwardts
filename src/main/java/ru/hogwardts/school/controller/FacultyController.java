package ru.hogwardts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.model.Student;
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
    public Faculty findFaculty(@PathVariable long id) {
        return faculties.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return faculties.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return faculties.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteStudent(@PathVariable long id) {
        faculties.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public Collection<Faculty> findFacultiesByColor(@RequestParam String color) {
        return faculties.findFacultiesByColor(color);
    }

    @GetMapping("findFacultyByColorOrName")
    public Collection<Faculty> findFacultiesByColorOrName(@RequestParam(required = false) String color,
                                                          @RequestParam(required = false) String name) {
        return faculties.findFacultiesByColorOrName(color, name);
    }

    @GetMapping("findFacultyByStudent")
    public Faculty findFacultyByStudent(@RequestParam long id) {
        return faculties.findFacultyByStudent(id);
    }

    //course-four-lesson-five
    @GetMapping("longest-name")
    public Faculty getFacultyWithLongestName() {
        return faculties.getFacultyWithLongestName();
    }
}
