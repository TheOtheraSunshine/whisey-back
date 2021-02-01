package com.tvz.diplomski.wisey.Student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvz.diplomski.wisey.Appointment.Appointment;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long idStudent;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact")
    private String contact;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "school")
    private String school;

    @Column(name = "age")
    private Integer age;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointments;
}
