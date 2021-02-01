package com.tvz.diplomski.wisey.Volunteer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvz.diplomski.wisey.RegistrationStatus.RegistrationStatus;
import com.tvz.diplomski.wisey.Role.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_volunteer")
    private Long idVolunteer;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_registration_status")
    @JsonIgnore
    private RegistrationStatus registrationStatus;

    @Column(name = "password")
    private String password;

}
