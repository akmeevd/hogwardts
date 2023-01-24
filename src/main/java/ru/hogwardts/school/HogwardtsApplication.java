package ru.hogwardts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hogwardts.school.model.Faculty;
import ru.hogwardts.school.repository.FacultyRepository;
import ru.hogwardts.school.service.FacultyService;

@SpringBootApplication
@OpenAPIDefinition
public class HogwardtsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HogwardtsApplication.class, args);
    }

}
