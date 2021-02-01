package com.tvz.diplomski.wisey.RegistrationStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registrationStatus")
public class RegistrationStatusController {

    private final RegistrationStatusRepository registrationStatusRepository;


    public RegistrationStatusController(RegistrationStatusRepository registrationStatusRepository) {
        this.registrationStatusRepository = registrationStatusRepository;
    }

    @GetMapping("/get")
    public List<RegistrationStatus> getAll() {
        List<RegistrationStatus> listOfRegistrationStatus = null;
        try{
            listOfRegistrationStatus = registrationStatusRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfRegistrationStatus;
    }
}
