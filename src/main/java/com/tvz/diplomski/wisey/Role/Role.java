package com.tvz.diplomski.wisey.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvz.diplomski.wisey.Volunteer.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "value")
    private String value;

    /*
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<Volunteer> volunteers;
     */
}
