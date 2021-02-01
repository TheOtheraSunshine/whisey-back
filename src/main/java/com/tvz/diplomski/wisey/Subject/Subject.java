package com.tvz.diplomski.wisey.Subject;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Long idSubject;

    @Column(name = "subject_name")
    private String subjectName;

}
