package com.casestudy_module4.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ROOM")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ACCOMMODATION_ID", nullable=false)
    private Accommodation accommodation;

    @OneToMany(mappedBy="ROOM")
    private List<RoomDate> roomDates;

    private String room_Type;
    private String room_name;
    private String bed_infor;
    private String img_path01;
    private String img_path02;
    private String img_path03;
    private double price;
    private int quantity;
    private int MAXPERSON;
}
