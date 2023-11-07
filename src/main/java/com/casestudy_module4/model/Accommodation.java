package com.casestudy_module4.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ACCOMMODATION")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="HOST_ID", nullable=false)
    private User user;

    @OneToMany(mappedBy="accommodation")
    private List<Room> roomList;

    private String name;
    private String type;
    private double star;
    private String description;
    private String address;
    private String city;
    private String country;
    private double rating;
}
