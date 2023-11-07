package com.casestudy_module4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="ROLE")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="role_name")
    private String name;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "role")
//    Collection<User> users;

}
