package com.tvz.diplomski.wisey.Appointment.model;

import lombok.Data;

@Data
public class AppointmentDTO {
    private final Long idAppointment;
    private final String appointmentDate;
    private final String timeFrom;
    private final String timeTo;
    private final String studentInfo;
    private final int reservedHours;
    private final String subjectName;
    private final String volunteerInfo;
}
