package ru.hogwardts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;


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

}
