package ru.hogwardts.school.controller;

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
    public Student deleteStudent(@PathVariable long id) {
        return students.deleteStudent(id);
    }

    @GetMapping()
    public Collection<Student> findStudentsByAge(@RequestParam int age) {
        return students.findStudentsByAge(age);
    }
}
