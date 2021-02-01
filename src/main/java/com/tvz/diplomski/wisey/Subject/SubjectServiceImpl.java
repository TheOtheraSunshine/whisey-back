package com.tvz.diplomski.wisey.Subject;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl (SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream().map(this::mapSubjectToDTO).collect(Collectors.toList());
    }

    private SubjectDTO mapSubjectToDTO(Subject subject) {
        return new SubjectDTO(
                subject.getIdSubject(),
                subject.getSubjectName()
        );
    }
}
