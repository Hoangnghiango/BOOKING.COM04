package com.casestudy_module4.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="roomdate")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room ROOM;

    @ManyToOne
    @JoinColumn(name="host_id", nullable=false)
    private User host;

    @ManyToOne
    @JoinColumn(name="acc_id", nullable=false)
    private Accommodation accommodation;

    private LocalDate date;
    private double price;
    private int quantity;
    private String status;
    private int order_number;
}
