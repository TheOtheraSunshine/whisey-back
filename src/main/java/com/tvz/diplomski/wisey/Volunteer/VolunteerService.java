package com.tvz.diplomski.wisey.Volunteer;

import com.tvz.diplomski.wisey.Student.StudentDTO;
import com.tvz.diplomski.wisey.Student.StudentRequest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface VolunteerService {

    List<VolunteerDTO> findAll();

    List<VolunteerDTO> findWaiting();

    Optional<VolunteerDTO> create(VolunteerRequest volunteerRequest);

    Optional<VolunteerDTO> update(Long idVolunteer, VolunteerRequest volunteerRequest);

    void deleteById(Long idVolunteer);

    VolunteerRequest register(VolunteerRequest volunteerRequest) throws Exception;
}
