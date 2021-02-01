package com.tvz.diplomski.wisey.Role;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO findByIdRole(Long idRole) {
        RoleDTO roleDTO = mapRoleToDTO(roleRepository.findByIdRole(idRole));
        return roleDTO;
    }

    private RoleDTO mapRoleToDTO(Role role) {
        return new RoleDTO(
                role.getIdRole(),
                role.getValue()
        );
    }
}
