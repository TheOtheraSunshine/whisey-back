package com.tvz.diplomski.wisey.Student;

import com.tvz.diplomski.wisey.Role.RoleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get")
    public List<StudentDTO> getAll() {
        List<StudentDTO> listOfStudents = null;
        try{
            listOfStudents = studentService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfStudents;
    }

    @GetMapping("/get/{idStudent}")
    public List<StudentDTO> getByIdStudent(@PathVariable final Long idStudent) {
        List<StudentDTO> studentDto = null;
        try{
            studentDto = studentService.findAllByIdStudent(idStudent);
            logger.info("Student " + studentDto.get(0).getFirstName() + " je ispravno dohvacen");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Moguce da student ne postoji");
        }
        return studentDto;
    }

    @Secured({"USER"})
    @PostMapping("/add")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody final StudentRequest studentRequest) {
        return studentService.create(studentRequest)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @PutMapping("/update/{idStudent}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable final Long idStudent, @RequestBody final StudentRequest studentRequest) {
            return studentService.update(idStudent, studentRequest)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{idStudent}")
    public void deleteById(@PathVariable Long idStudent) {
        try {
            studentService.deleteById(idStudent);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("Greska!");
        }
    }


}
