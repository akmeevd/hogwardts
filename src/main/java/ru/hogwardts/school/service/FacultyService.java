package ru.hogwardts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;

    }

    public Faculty createFaculty(Faculty faculty) {
        getLogger("createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        getLogger("findFaculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        getLogger("editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        getLogger("deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findFacultiesByColor(String color) {
        getLogger("findFacultiesByColor");
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    public Collection<Faculty> findFacultiesByColorOrName(String color, String name) {
        getLogger("findFacultiesByColorOrName");
        return facultyRepository.findFacultiesByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Faculty findFacultyByStudent(long id) {
        getLogger("findFacultyByStudent");
        for (Faculty faculty : facultyRepository.findAll()) {
            for (Student student : faculty.getStudents()) {
                if (student.getId() == id) {
                    return student.getFaculty();
                }
            }
        }
        return null;
    }
    private void getLogger(String methodName) {
        logger.debug("method called: " + methodName);
    }
}
