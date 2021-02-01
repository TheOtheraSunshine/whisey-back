package com.tvz.diplomski.wisey.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/get")
    private List<SubjectDTO> getAll() {
        List<SubjectDTO> listOfSubjects = null;
        try{
            listOfSubjects = subjectService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfSubjects;
    }
}
