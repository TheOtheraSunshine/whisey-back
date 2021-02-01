package com.tvz.diplomski.wisey.Role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get/{idRole}")
    public RoleDTO getRoleById(@PathVariable final Long idRole) {

        RoleDTO roleDto = null;

        try{
            roleDto = roleService.findByIdRole(idRole);
            logger.info("Rola je ispravno dohvacena");
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.error("Moguce da rola ne postoji");
        }
        return roleDto;
    }


}
