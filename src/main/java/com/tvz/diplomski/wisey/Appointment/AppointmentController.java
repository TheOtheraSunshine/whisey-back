package com.tvz.diplomski.wisey.Appointment;

import com.tvz.diplomski.wisey.Appointment.model.AppointmentDTO;
import com.tvz.diplomski.wisey.Appointment.model.FlowChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Flow;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/get")
    public List<AppointmentDTO> getAll() {
        List<AppointmentDTO> listOfAppointments = null;
        try{
            listOfAppointments = appointmentService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfAppointments;
    }

    @GetMapping("/getChartData")
    public FlowChartDTO getAllData() {
        FlowChartDTO flowChartDTO= null;
        try {
            flowChartDTO = appointmentService.findAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flowChartDTO;
    }

    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody final AppointmentRequest appointmentRequest) {
        return appointmentService.create(appointmentRequest)
                .map(
                        appointmentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(appointmentDTO)
                ).orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{idAppointment}")
    public void deleteById(@PathVariable Long idAppointment) {
        try {
            appointmentService.deleteById(idAppointment);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
