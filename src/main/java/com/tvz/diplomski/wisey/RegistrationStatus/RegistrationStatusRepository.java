package com.tvz.diplomski.wisey.RegistrationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationStatusRepository extends JpaRepository<RegistrationStatus, Long> {
    List<RegistrationStatus> findAll();

    RegistrationStatus findByIdRegistrationStatus(Long idRegistrationStatus);
}
