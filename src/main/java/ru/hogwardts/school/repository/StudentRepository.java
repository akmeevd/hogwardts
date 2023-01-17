package ru.hogwardts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwardts.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
