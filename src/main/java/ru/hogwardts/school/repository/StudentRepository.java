package ru.hogwardts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByAgeBetween(int min, int max);

    Collection<Student> findStudentsByFaculty_Id(long id);

}
