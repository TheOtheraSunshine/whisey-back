package com.tvz.diplomski.wisey.LoginRegistraion;

import com.tvz.diplomski.wisey.Volunteer.Volunteer;
import com.tvz.diplomski.wisey.Volunteer.VolunteerDTO;
import com.tvz.diplomski.wisey.Volunteer.VolunteerRepository;
import com.tvz.diplomski.wisey.Volunteer.VolunteerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    VolunteerServiceImpl volunteerServiceImpl;

    @GetMapping
    public ResponseEntity<VolunteerDTO> login(Principal principal) {
        if(principal != null) {
            Volunteer volunteer = volunteerRepository.findByUsername(principal.getName());

            String status = volunteer.getRegistrationStatus().getValue();

            if(!status.equals("Approved")) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            if(volunteer != null) {
                VolunteerDTO volunteerDTO = volunteerServiceImpl.mapVolunteerToDTO(volunteer);
                return new ResponseEntity<>(volunteerDTO, HttpStatus.OK);
            }
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
