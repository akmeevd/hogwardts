package ru.hogwardts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwardts.school.model.Student;
import ru.hogwardts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findStudentsByAge(int age) {
        return studentRepository.findAll();
    }


}
