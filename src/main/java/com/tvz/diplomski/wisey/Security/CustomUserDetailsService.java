package com.tvz.diplomski.wisey.Security;

import com.tvz.diplomski.wisey.Volunteer.Volunteer;
import com.tvz.diplomski.wisey.Volunteer.VolunteerController;
import com.tvz.diplomski.wisey.Volunteer.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Volunteer volunteer = volunteerRepository.findByUsername(username);

        if(volunteer == null) {
            logger.info("Username " + username + " not found");
            throw new UsernameNotFoundException("Korisničko ime " + username + " nije pronađeno");
        } else if (volunteer.getRegistrationStatus().getValue().equals("Denied")
                || volunteer.getRegistrationStatus().getValue().equals("Waiting")) {
            logger.info("Username " + username + " is not approved");
            throw new UsernameNotFoundException("Korisničko ime " + username + " nije odobreno");
        }

        return new User(volunteer.getUsername(), volunteer.getPassword(), getGrantedAuthorities(volunteer));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(Volunteer volunteer){
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (volunteer.getRole().getValue().equals("Admin")){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else if (volunteer.getRole().getValue().equals("User")){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return grantedAuthorities;
    }
}
