package com.tvz.diplomski.wisey.Appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvz.diplomski.wisey.AppointmentStatus.AppointmentStatus;
import com.tvz.diplomski.wisey.Student.Student;
import com.tvz.diplomski.wisey.Subject.Subject;
import com.tvz.diplomski.wisey.Volunteer.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment")
    private Long idAppointment;

    @Column(name = "fromDT")
    private Calendar from;

    @Column(name = "toDT")
    private Calendar to;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "id_appointment_status")
    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "id_volunteer")
    private Volunteer volunteer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_has_appointment",
            joinColumns = @JoinColumn(name = "id_appointment"),
            inverseJoinColumns = @JoinColumn(name = "id_student"))
    private List<Student> students;

    @Column(name = "reserved_hours")
    private int reservedHours;

}
