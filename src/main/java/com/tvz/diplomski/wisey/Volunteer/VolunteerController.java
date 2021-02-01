package com.tvz.diplomski.wisey.Volunteer;

import com.tvz.diplomski.wisey.Constants.RegistraionConst;
import com.tvz.diplomski.wisey.Role.RoleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    private static final Logger logger = LoggerFactory.getLogger(VolunteerController.class);
    private final VolunteerService volunteerService;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerController(VolunteerService volunteerService, VolunteerRepository volunteerRepository) {
        this.volunteerService = volunteerService;
        this.volunteerRepository = volunteerRepository;
    }

    @GetMapping("/get")
    public List<VolunteerDTO> getAll() {
        List<VolunteerDTO> listOfVolunteers = null;
        try{
            listOfVolunteers = volunteerService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfVolunteers;
    }

    @GetMapping("/get/{idVolunteer}")
    public Volunteer getVolunteerById(@PathVariable final Long idVolunteer) {
        Volunteer volunteer = null;
        try {
            volunteer = volunteerRepository.findByIdVolunteer(idVolunteer);
            logger.info( "Volonter " + volunteer.getFirstName() + " je ispravno dohvacen");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Moguce da volonter ne postoji");
        }
        return volunteer;
    }

    @GetMapping("/getWaiting")
    public List<VolunteerDTO> getWaiting() {
        List<VolunteerDTO> listOfVolunteers = null;
        try{
            listOfVolunteers = volunteerService.findWaiting();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfVolunteers;
    }

    @PutMapping("/update/{idVolunteer}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable final Long idVolunteer, @RequestBody final VolunteerRequest volunteerRequest) {
        return volunteerService.update(idVolunteer, volunteerRequest)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{idVolunteer}")
    public void deleteById(@PathVariable Long idVolunteer) {
        volunteerService.deleteById(idVolunteer);
    }


}
