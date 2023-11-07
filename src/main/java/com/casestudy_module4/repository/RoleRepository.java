package com.casestudy_module4.repository;

import com.casestudy_module4.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(String name);
}
