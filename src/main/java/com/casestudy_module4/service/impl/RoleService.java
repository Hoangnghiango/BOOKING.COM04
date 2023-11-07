package com.casestudy_module4.service.impl;

import com.casestudy_module4.model.Role;
import com.casestudy_module4.repository.RoleRepository;
import com.casestudy_module4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }


}
