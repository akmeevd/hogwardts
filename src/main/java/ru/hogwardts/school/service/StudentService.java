package ru.hogwardts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwardts.school.model.Student;
import ru.hogwardts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<Student> students = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                students.add(student);
            }
        }
        return students;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    public Collection<Student> findStudentsByFaculty_Id(long id) {
        return studentRepository.findStudentsByFaculty_Id(id);
    }
}
