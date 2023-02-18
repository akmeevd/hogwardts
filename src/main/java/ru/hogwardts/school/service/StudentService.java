package ru.hogwardts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwardts.school.model.Student;
//import ru.hogwardts.school.model.StudentsByDescOrder;
import ru.hogwardts.school.model.StudentsByDescOrder;
import ru.hogwardts.school.repository.AvatarRepository;
import ru.hogwardts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public void getSixStudentsByParallelThreads() {
        List<Student> students = new ArrayList<>();
        students.addAll(studentRepository.findAll());
        System.out.println(students.get(0));
        System.out.println(students.get(1));
        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();
        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();
    }

    public synchronized void getSixStudentsBySynchronizedParallelThreads() {
        getSixStudentsByParallelThreads();
    }

    private void getLogger(String methodName) {
        logger.debug("method called: " + methodName);
    }

}
