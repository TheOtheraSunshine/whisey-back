package com.tvz.diplomski.wisey.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDTO> findAll();

    List<StudentDTO> findAllByIdStudent(Long IdStudent);

    Student findByIdStudent(Long idStudent);

    Optional<StudentDTO> create(StudentRequest studentRequest);

    Optional<StudentDTO> update(Long idStudent, StudentRequest studentRequest);

    void deleteById(Long idStudent);

}
