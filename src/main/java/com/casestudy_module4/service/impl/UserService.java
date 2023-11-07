package com.casestudy_module4.service.impl;


import com.casestudy_module4.dto.RegisterDTO;
import com.casestudy_module4.dto.UserDTO;
import com.casestudy_module4.model.Role;
import com.casestudy_module4.model.User;
import com.casestudy_module4.repository.RoleRepository;
import com.casestudy_module4.repository.UserRepository;
import com.casestudy_module4.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;



    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public boolean save(User registerDTO) {
        User user = new User();
        Iterable<User> userList =  userRepository.findAll();

        for (User userExist : userList) {
            if (Objects.equals(registerDTO.getGmail(), userExist.getGmail()) || Objects.equals(registerDTO.getUsername(), userExist.getUsername())) {
                return false;
            }
        }
        user.setUsername(registerDTO.getUsername());
//        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setGmail(registerDTO.getGmail());
        user.setRoles(registerDTO.getRoles());
        userRepository.save(user); // api

        return true;
    }

    @Override
    public boolean saveAccordingRole(RegisterDTO registerDTO, String roleName) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setGmail(registerDTO.getEmail());
        user.setRoles(Set.of(roleRepository.findByName(roleName)));
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> userList = (List<User>) userRepository.findAll();
        for(User user : userList){
            userDTOS.add(toDTO(user));
        }
        return userDTOS;
    }

    @Override
    public User findByUsernameAfterLogin() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  userRepository.findByUsername(user.getUsername()).get();

    }

    public UserDTO findById(Long id) {
        Iterable<User> userList =  userRepository.findAll();

        for (User user : userList) {
            if (Objects.equals(user.getId(), id)) {
                return toDTO(user);
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }

//        Optional<User> user = userRepository.findByUsername(username).get()
//        if(!user.isPresent()){
//            throw new UsernameNotFoundException("user is not found");
//        }
//        return UserPrinciple.build(user.get());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(),
//                true,true,true,true,
//                getAuthorities("ROLE_USER"));


    public UserDTO toDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword(), user.getRoles());
    }
}
