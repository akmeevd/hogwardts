package ru.hogwardts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwardts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long id = 0;

    public Student createStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    public Collection<Student> findStudentsByAge(int age) {
        Collection<Student> studentCollection = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                studentCollection.add(student);
            }
        }
        return studentCollection;
    }


}
