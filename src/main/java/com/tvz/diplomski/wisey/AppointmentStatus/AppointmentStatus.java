package com.tvz.diplomski.wisey.AppointmentStatus;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "appointment_status")
public class AppointmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment_status")
    private Long idAppointmentStatus;

    @Column(name = "done")
    private boolean done;

}
