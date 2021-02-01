package com.tvz.diplomski.wisey.LoginRegistraion;

import com.tvz.diplomski.wisey.Volunteer.VolunteerDTO;
import com.tvz.diplomski.wisey.Volunteer.VolunteerRequest;
import com.tvz.diplomski.wisey.Volunteer.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private final VolunteerService volunteerService;

    public RegistrationController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public ResponseEntity<VolunteerDTO> addVolunteer(@RequestBody final VolunteerRequest volunteerRequest) throws Exception {

        return volunteerService.create(volunteerService.register(volunteerRequest))
                .map(
                        volunteerDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(volunteerDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }
}
