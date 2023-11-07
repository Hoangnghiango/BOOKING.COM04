package com.casestudy_module4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USER")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String username;
    private String password;
    private String gmail;
    private String avatar;
    private double balance;
    private String address;
    private String city;
    private String country;
    @OneToMany(mappedBy="user")
    private List<Accommodation> accommodationList;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();
    public void addRole(Role role) {
        this.roles.add(role);
    }
    @OneToMany(mappedBy="USER")
    private Set<Booking> bookings;
}
