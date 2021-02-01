package com.tvz.diplomski.wisey.RegistrationStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvz.diplomski.wisey.Volunteer.Volunteer;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "registration_status")
public class RegistrationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registration_status")
    private Long idRegistrationStatus;

    @Column(name = "value")
    private String value;

    /*
    @OneToMany(mappedBy = "registrationStatus")
    @JsonIgnore
    private List<Volunteer> volunteers;
    */
}
