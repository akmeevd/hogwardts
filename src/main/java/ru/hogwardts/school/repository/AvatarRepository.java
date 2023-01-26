package ru.hogwardts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwardts.school.model.Avatar;

import java.util.Optional;


public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findAvatarByStudentId(Long studentId);
}
