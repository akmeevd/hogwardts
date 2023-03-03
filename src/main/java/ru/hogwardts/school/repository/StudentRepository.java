package ru.hogwardts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwardts.school.model.Student;
import ru.hogwardts.school.model.StudentsByDescOrder;
//import ru.hogwardts.school.model.StudentsByDescOrder;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByAgeBetween(int min, int max);

    Collection<Student> findStudentsByFaculty_Id(long id);

    @Query(value = "SELECT count (id) from student", nativeQuery = true)
    Integer getCountOfAllStudents();

    @Query(value = "SELECT avg (age) from student", nativeQuery = true)
    Double getAverageAgeOfStudents();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<StudentsByDescOrder> getFiveStudentsByDescOrder();



}
