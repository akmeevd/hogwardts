package ru.hogwardts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwardts.school.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
