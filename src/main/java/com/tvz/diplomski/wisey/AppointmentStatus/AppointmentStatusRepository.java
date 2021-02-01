package com.tvz.diplomski.wisey.AppointmentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Long> {

    List<AppointmentStatus> findAll();

    AppointmentStatus findByIdAppointmentStatus(Long idAppointmentStatus);

}
