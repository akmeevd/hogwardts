package ru.hogwardts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwardts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long id = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> findFacultiesByColor(String color) {
        Collection<Faculty> facultyCollection = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (faculty.getColor().equals(color)) {
                facultyCollection.add(faculty);
            }
        }
        return facultyCollection;
    }

}
