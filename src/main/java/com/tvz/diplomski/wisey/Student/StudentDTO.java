package com.tvz.diplomski.wisey.Student;

import com.tvz.diplomski.wisey.Appointment.Appointment;
import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private final Long idStudent;
    private final String firstName;
    private final String lastName;
    private final String contact;
    private final Integer grade;
    private final String school;
    private final Integer age;
}
