package com.casestudy_module4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="CART")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int customer_id;
    private double total;
    @OneToMany(mappedBy="cart")
    private Set<CartLine> cartLines;

    @OneToOne(mappedBy = "cart")
    private Booking booking;

}
