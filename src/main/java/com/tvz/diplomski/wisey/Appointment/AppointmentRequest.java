package com.tvz.diplomski.wisey.Appointment;

import com.tvz.diplomski.wisey.AppointmentStatus.AppointmentStatus;
import com.tvz.diplomski.wisey.Student.Student;
import com.tvz.diplomski.wisey.Subject.Subject;
import com.tvz.diplomski.wisey.Volunteer.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private Long idAppointment;
    private String dateFrom;
    private String dateTo;
    private Long idSubject;
    private Long idAppointmentStatus;
    private Long idVolunteer;
    private Long idStudent;
    private int reservedHours;

}
