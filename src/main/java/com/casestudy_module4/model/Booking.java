package com.casestudy_module4.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="BOOKING")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private User USER;

    private LocalDate from_date;
    private LocalDate to_date;

    @ManyToOne
    @JoinColumn(name="host_id", nullable=false)
    private User host;

    private int accId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    private String visitor;
    private String email;
    private LocalDate booking_date;
    private double total;
}
