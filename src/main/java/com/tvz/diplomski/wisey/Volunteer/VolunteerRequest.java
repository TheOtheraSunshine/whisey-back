package com.tvz.diplomski.wisey.Volunteer;

import com.tvz.diplomski.wisey.RegistrationStatus.RegistrationStatus;
import com.tvz.diplomski.wisey.Role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerRequest {
    private Long idVolunteer;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String username;
    private Long idRole;
    private Long idRegistrationStatus;
    private String password;
}
