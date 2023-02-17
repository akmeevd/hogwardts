package ru.hogwardts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.model.Student;
//import ru.hogwardts.school.model.StudentsByDescOrder;
import ru.hogwardts.school.model.StudentsByDescOrder;
import ru.hogwardts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        getLogger("createStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        getLogger("findStudent");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        getLogger("editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        getLogger("deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findStudentsByAge(int age) {
        getLogger("findStudentsByAge");
        List<Student> students = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                students.add(student);
            }
        }
        return students;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        getLogger("findByAgeBetween");
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    public Collection<Student> findStudentsByFaculty_Id(long id) {
        getLogger("findStudentsByFaculty_Id");
        return studentRepository.findStudentsByFaculty_Id(id);
    }

    public Integer getCountOfAllStudent() {
        getLogger("getCountOfAllStudent");
        return studentRepository.getCountOfAllStudents();
    }

    public Double getAverageAgeOfStudents() {
        getLogger("getAverageAgeOfStudents");
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<StudentsByDescOrder> getFiveStudentsByDescOrder() {
        getLogger("getFiveStudentsByDescOrder");
        return studentRepository.getFiveStudentsByDescOrder();
    }

    //course-four-lesson-five
    public List<String> getNamesOfStudentsByLetterA() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .parallel()
                .filter(s -> s.getName().substring(0, 1).equalsIgnoreCase("a"))
                .map(s -> s.getName().toUpperCase()).toList();
    }

    //course-four-lesson-five
    public OptionalDouble getAverageAgeForAllStudents() {
        List<Student> students = studentRepository.findAll();
       return students
               .stream()
               .parallel()
               .mapToDouble(Student::getAge)
               .average();
    }

    private void getLogger(String methodName) {
        logger.debug("method called: " + methodName);
    }

}
