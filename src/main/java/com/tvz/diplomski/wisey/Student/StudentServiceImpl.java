package com.tvz.diplomski.wisey.Student;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllByIdStudent(Long IdStudent) {
        return studentRepository.findAllByIdStudent(IdStudent).stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public Student findByIdStudent(Long idStudent) {
        return studentRepository.findByIdStudent(idStudent);
    }

    @Override
    public Optional<StudentDTO> create(StudentRequest studentRequest) {
        Student student = mapRequestToStudent(studentRequest);
        return Optional.of(studentRepository.save(student)).map(this::mapStudentToDTO);
    }

    @Override
    public Optional<StudentDTO> update(Long idStudent, StudentRequest studentRequest) {
        Student student =mapRequestToStudent(studentRequest);
        student.setIdStudent(idStudent);
        return Optional.of(studentRepository.save(student)).map(this::mapStudentToDTO);
    }

    @Override
    public void deleteById(final Long idStudent) {
        studentRepository.deleteById(idStudent);
    }

    private StudentDTO mapStudentToDTO(Student student) {
        return new StudentDTO(
                student.getIdStudent(),
                student.getFirstName(),
                student.getLastName(),
                student.getContact(),
                student.getGrade(),
                student.getSchool(),
                student.getAge());
    }

    private Student mapRequestToStudent(StudentRequest studentRequest){
        return new Student(
                studentRequest.getIdStudent(),
                studentRequest.getFirstName(),
                studentRequest.getLastName(),
                studentRequest.getContact(),
                studentRequest.getGrade(),
                studentRequest.getSchool(),
                studentRequest.getAge(),
                null);
    }
}
