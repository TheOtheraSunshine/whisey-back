package com.tvz.diplomski.wisey.Student;

import com.tvz.diplomski.wisey.Appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private Long idStudent;
    private String firstName;
    private String lastName;
    private String contact;
    private Integer grade;
    private String school;
    private Integer age;
}
