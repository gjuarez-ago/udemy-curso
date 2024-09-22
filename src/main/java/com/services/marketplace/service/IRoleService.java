package com.services.marketplace.service;

import java.util.List;

import com.services.marketplace.entity.Role;

public interface IRoleService {

    public Role getRoleById(Long id);

    public Role createRole(Role role);

    public List<Role> getAllRoles();

    public Role updateRole(Long id, Role roleDetails);

    public void deleteRole(Long id);
    
}
