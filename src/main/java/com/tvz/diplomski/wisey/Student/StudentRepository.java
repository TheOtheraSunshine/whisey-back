package com.tvz.diplomski.wisey.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();

    List<Student> findAllByIdStudent(Long idStudent);

    Student findByIdStudent(Long idStudent);

}
