package com.casestudy_module4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {
    @NotEmpty(message = "Not Empty ")
    private String name;

    @NotEmpty(message = "Not Empty")
    private String type;

    @NotNull(message = "Not Empty")
    @DecimalMax(value = "6.0", message = "Star must be no greater than 6.0")
    private double star;

    @NotEmpty(message = "Not Empty")
    private String description;

    @NotEmpty(message = "Not Empty")
    private String address;

    @NotEmpty(message = "Not Empty")
    private String city;

    @NotEmpty(message = "Thiếu password")
    private String country;
}

