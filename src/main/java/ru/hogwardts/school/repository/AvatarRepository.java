package ru.hogwardts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwardts.school.model.Avatar;

import java.util.Collection;
import java.util.Optional;


public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {
    Avatar findAvatarByStudentId(Long studentId);

}
