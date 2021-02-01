package com.tvz.diplomski.wisey.Volunteer;

import com.tvz.diplomski.wisey.RegistrationStatus.RegistrationStatus;
import com.tvz.diplomski.wisey.Role.Role;
import lombok.Data;

@Data
public class VolunteerDTO {
    private final Long idVolunteer;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String contact;
    private final String username;
    private final Role role;
    private final RegistrationStatus registrationStatus;
}
