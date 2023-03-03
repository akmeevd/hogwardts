package ru.hogwardts.school.repository;

import org.postgresql.hostchooser.HostRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.model.Student;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findFacultiesByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

    Collection<Faculty> findFacultiesByColorIgnoreCase(String color);
}
