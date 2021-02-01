package com.tvz.diplomski.wisey.Volunteer;

import com.tvz.diplomski.wisey.Constants.RegistraionConst;
import com.tvz.diplomski.wisey.Constants.RoleConst;
import com.tvz.diplomski.wisey.RegistrationStatus.RegistrationStatus;
import com.tvz.diplomski.wisey.RegistrationStatus.RegistrationStatusRepository;
import com.tvz.diplomski.wisey.Role.Role;
import com.tvz.diplomski.wisey.Role.RoleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final VolunteerRepository volunteerRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final RegistrationStatusRepository registrationStatusRepository;

    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, RoleRepository roleRepository, RegistrationStatusRepository registrationStatusRepository) {
        this.volunteerRepository = volunteerRepository;
        this.roleRepository = roleRepository;
        this.registrationStatusRepository = registrationStatusRepository;
    }

    @Override
    public List<VolunteerDTO> findAll() {
         return volunteerRepository.findAll().stream().map(this::mapVolunteerToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VolunteerDTO> findWaiting() {

        List<VolunteerDTO> volunteers = volunteerRepository.findAll().stream().map(this::mapVolunteerToDTO).collect(Collectors.toList());

        for(int i=0;i<volunteers.size();i++) {
            if(volunteers.get(i).getRegistrationStatus().getValue().equals("Approved") ||
                    volunteers.get(i).getRegistrationStatus().getValue().equals("Denied")) {
                volunteers.remove(i);
                i--;
            }
        }

        return volunteers;
    }

    @Override
    public Optional<VolunteerDTO> create(VolunteerRequest volunteerRequest) {
        Volunteer volunteer =mapRequestToVolunteer(volunteerRequest);
        return Optional.of(volunteerRepository.save(volunteer)).map(this::mapVolunteerToDTO);
    }

    @Override
    public Optional<VolunteerDTO> update(Long idVolunteer, VolunteerRequest volunteerRequest) {
        Volunteer volunteer = mapRequestToVolunteer(volunteerRequest);
        volunteer.setIdVolunteer(idVolunteer);
        return Optional.of(volunteerRepository.save(volunteer)).map(this::mapVolunteerToDTO);
    }

    @Override
    public void deleteById(Long idVolunteer) {
        volunteerRepository.deleteById(idVolunteer);
    }

    @Override
    public VolunteerRequest register(VolunteerRequest volunteerRequest) throws Exception {
        if(!volunteerRequest.getUsername().equals("") && volunteerRepository.existsByUsername(volunteerRequest.getUsername())) {
            throw new Exception("Korisničko ime " + volunteerRequest.getUsername() + " već postoji");
        }

        if(volunteerRepository.existsByEmail(volunteerRequest.getEmail())) {
            throw new Exception("E-mail adresa " + volunteerRequest.getEmail() + " već postoji");
        }

        volunteerRequest.setIdRole(RoleConst.USER);
        volunteerRequest.setIdRegistrationStatus(RegistraionConst.WAITING);
        volunteerRequest.setPassword(bCryptPasswordEncoder.encode(volunteerRequest.getPassword()));

        return volunteerRequest;
    }

    public VolunteerDTO mapVolunteerToDTO(Volunteer volunteer) {
        return new VolunteerDTO(
                volunteer.getIdVolunteer(),
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getEmail(),
                volunteer.getContact(),
                volunteer.getUsername(),
                volunteer.getRole(),
                volunteer.getRegistrationStatus());
    }

    private Volunteer mapRequestToVolunteer(VolunteerRequest volunteerRequest){

        Role role = roleRepository.findByIdRole(volunteerRequest.getIdRole());
        RegistrationStatus registrationStatus = registrationStatusRepository.findByIdRegistrationStatus(volunteerRequest.getIdRegistrationStatus());

        return new Volunteer(
                volunteerRequest.getIdVolunteer(),
                volunteerRequest.getFirstName(),
                volunteerRequest.getLastName(),
                volunteerRequest.getEmail(),
                volunteerRequest.getContact(),
                volunteerRequest.getUsername(),
                role,
                registrationStatus,
                volunteerRequest.getPassword()
                );
    }
}
