package ru.hogwardts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.model.Student;
import ru.hogwardts.school.repository.FacultyRepository;
import ru.hogwardts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;

    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findFacultiesByColor(String color) {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findFacultiesByColorOrName(String color, String name) {
        return facultyRepository.findFacultiesByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Faculty findFacultyByStudent(long id) {
        for (Faculty faculty : facultyRepository.findAll()) {
            for (Student student : faculty.getStudents()) {
                if (student.getId() == id) {
                    return student.getFaculty();
                }
            }
        }
        return null;
    }
}
