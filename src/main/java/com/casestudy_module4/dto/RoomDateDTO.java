package com.casestudy_module4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDateDTO {
    @Future(message = "Please dont set day in the past")
    private LocalDate date;

    @Size(min = 100000,message = "Price must be greater than 100.000 VND")
    private double price;
    @Size(min = 1, message = "Quantity must be greater than 1")
    private int quantity;
    @NotEmpty(message = "This field is empty")
    private String status;
}
