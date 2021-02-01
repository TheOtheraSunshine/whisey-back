package com.tvz.diplomski.wisey.Appointment;


import com.tvz.diplomski.wisey.Appointment.model.AppointmentDTO;
import com.tvz.diplomski.wisey.Appointment.model.FlowChartDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<AppointmentDTO> findAll();

    FlowChartDTO findAllData() throws ParseException;

    Optional<AppointmentDTO> create(AppointmentRequest appointmentRequest);

    void deleteById(Long idAppointment);
}
