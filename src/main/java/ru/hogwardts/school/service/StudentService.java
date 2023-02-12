package ru.hogwardts.school.service;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Before(value = "createStudent")

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
        getLogger("findStudentByAge");
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
        getLogger("getCountOfAllStudent");
        return studentRepository.getAverageAgeOfStudents();
    }


    public List<StudentsByDescOrder> getFiveStudentsByDescOrder() {
        getLogger("getCountOfAllStudent");
        return studentRepository.getFiveStudentsByDescOrder();
    }

    private void getLogger(String methodName) {
        logger.info("method called: " + methodName);
    }
}
