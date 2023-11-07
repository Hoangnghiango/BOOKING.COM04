package com.casestudy_module4.service;


import com.casestudy_module4.dto.RegisterDTO;
import com.casestudy_module4.dto.UserDTO;
import com.casestudy_module4.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByUserName(String userName);
    boolean save(User registerDTO);

    boolean saveAccordingRole(RegisterDTO registerDTO, String roleName);
    List<UserDTO> findAll();

    User findByUsernameAfterLogin();



}
