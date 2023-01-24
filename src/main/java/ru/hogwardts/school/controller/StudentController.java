package ru.hogwardts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwardts.school.model.Student;
import ru.hogwardts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService students;

    public StudentController(StudentService students) {
        this.students = students;
    }

    @GetMapping("{id}")
    public Student findStudent(@PathVariable long id) {
        return students.findStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return students.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return students.editStudent(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        students.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Collection<Student> findStudentsByAge(@RequestParam int age) {
        return students.findStudentsByAge(age);
    }

    @GetMapping("findStudentsByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return students.findByAgeBetween(min, max);
    }

    @GetMapping("findStudentByFaculty_Id")
    public Collection<Student> findStudentsByFaculty_Id(@RequestParam long id) {
        return students.findStudentsByFaculty_Id(id);
    }
}
