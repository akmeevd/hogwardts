package ru.hogwardts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwardts.school.model.Student;
//import ru.hogwardts.school.model.StudentsByDescOrder;
import ru.hogwardts.school.model.StudentsByDescOrder;
import ru.hogwardts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @GetMapping("students-by-age-between")
    public Collection<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return students.findByAgeBetween(min, max);
    }

    @GetMapping("student-by-faculty-id")
    public Collection<Student> findStudentsByFaculty_Id(@RequestParam long id) {
        return students.findStudentsByFaculty_Id(id);
    }

    @GetMapping("count-of-all-students")
    public Integer getCountAllStudents() {
        return students.getCountOfAllStudent();
    }

    @GetMapping("average-age-of-students")
    public Double getAverageAgeOfStudents() {
        return students.getAverageAgeOfStudents();
    }

    @GetMapping("students-by-descending-order")
    public List<StudentsByDescOrder> getFiveStudentsByDescOrder() {
        return students.getFiveStudentsByDescOrder();
    }

    @GetMapping("six-students-by-parallel-threads")
    public void getSixStudentsByParallelThreads() {
        students.getSixStudentsByParallelThreads();
    }

    @GetMapping("six-students-by-synchronized-parallel-threads")
    public void getSixStudentsBySynchronizedParallelThreads() {
        students.getSixStudentsBySynchronizedParallelThreads();
    }

    //course-four-lesson-five
    @GetMapping("students-by-letter-a")
    public List<String> getNamesOfStudentsByLetterA() {
        return students.getNamesOfStudentsByLetterA();
    }

    //course-four-lesson-five
    @GetMapping("average-age")
    public OptionalDouble getAverageAgeForAllStudents() {
        return students.getAverageAgeForAllStudents();
    }

    @GetMapping("sum")
    public int getSum() {
        int finishNumber = 1_000_000;
        return IntStream
                .range(1,1_000_000 + 1)
                .sum();
    }
}
