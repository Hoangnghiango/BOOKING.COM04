package com.casestudy_module4.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="CARTLINE")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;

    private int roomId;
    private int quantity;
    private double subtotal;
    private LocalDate fromDate;
    private LocalDate toDate;
}
