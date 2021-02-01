package com.tvz.diplomski.wisey.Volunteer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer,Long> {
    List<Volunteer> findAll();

    Volunteer findByIdVolunteer(Long idVolunteer);

    Volunteer findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
