package com.casestudy_module4.service;

import com.casestudy_module4.model.Role;

public interface IRoleService {
    void save(Role role);
    Role findByName(String name);
//    void plush();

}
