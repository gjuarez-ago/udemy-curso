package com.services.marketplace.service.impl;

import com.services.marketplace.entity.Role;
import com.services.marketplace.repository.RoleRepository;
import com.services.marketplace.service.IRoleService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    // Crear un nuevo rol
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Obtener un rol por ID
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

    }

    // Obtener todos los roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Actualizar un rol existente
    public Role updateRole(Long id, Role roleDetails) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    // Eliminar un rol por ID
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
    }
}